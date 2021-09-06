package com.licenta.dto.convertor;

import com.licenta.dto.UserDTO;
import com.licenta.models.User;
import com.licenta.repositories.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

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
            dto = new UserDTO(
                    user.getId(),
                    user.getGender(),
                    user.getBirthdate(),
                    user.getHeightInCm(),
                    user.getActivityLevel(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getMaintenanceCalories(),
                    user.getWeight(),
                    user.getDesiredWeight(),
                    user.getCreatedAt(),
                    user.getDiet(),
                    user.getFat(),
                    user.getCarbs(),
                    user.getProtein(),
                    user.getPasswordHash()
            );
        }
        return dto;
    }


}
