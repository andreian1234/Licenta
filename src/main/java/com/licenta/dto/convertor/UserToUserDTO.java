package com.licenta.dto.convertor;

import com.licenta.dto.UserDTO;
import com.licenta.models.User;
import com.licenta.models.Weight;
import com.licenta.repositories.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public final class UserToUserDTO implements Converter<User, UserDTO> {

    private final WeightRepository weightRepository;

    @Autowired
    public UserToUserDTO(final WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    @Override

    public UserDTO convert(final User user) {
        UserDTO dto = null;
        if (user != null) {
            final double currentWeight = getCurrentWeight(user);
            dto = new UserDTO(
                    user.getId(),
                    user.getGender(),
                    user.getBirthdate(),
                    user.getHeightInCm(),
                    user.getActivityLevel(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    currentWeight,
                    getBmi(user, currentWeight),
                    getMaintenanceCalories(user, currentWeight)
            );
        }
        return dto;
    }

    private double getCurrentWeight(final User user) {
        final List<Weight> weights = weightRepository.findByUserOrderByDateDesc(user);
        double currentWeight = 0;
        if (weights != null && !weights.isEmpty()) {
            currentWeight = weights.get(0).getPounds();
        }
        return currentWeight;
    }

    private double getBmi(
            final User user,
            final double currentWeight
    ) {
        double bmi = 0;
        if (currentWeight != 0 && user.getHeightInCm() != 0) {
            bmi = (currentWeight * 703) / (user.getHeightInCm() * user.getHeightInCm());
        }
        return bmi;
    }

    private int getMaintenanceCalories(
            final User user,
            final double currentWeight
    ) {
        int maintenanceCalories = 0;
        final long age = ChronoUnit.YEARS.between(user.getBirthdate(), LocalDate.now());
        if (user.getGender() != null
                && currentWeight != 0
                && user.getHeightInCm() != 0
                && age != 0
                && user.getActivityLevel() != null
        ) {
            final double centimeters = user.getHeightInCm() * 2.54f;
            final double kilograms = currentWeight / 2.2f;
            final double adjustedWeight = user.getGender().equals(User.Gender.FEMALE) ? 655f + (9.6f * kilograms) : 66f + (13.7f * kilograms);
            final double adjustedHeight = user.getGender().equals(User.Gender.FEMALE) ? 1.7f * centimeters : 5f * centimeters;
            final float adjustedAge = user.getGender().equals(User.Gender.FEMALE) ? 4.7f * age : 6.8f * age;
            maintenanceCalories = (int) ((adjustedWeight + adjustedHeight - adjustedAge) * user.getActivityLevel().getValue());
        }
        return maintenanceCalories;
    }


}
