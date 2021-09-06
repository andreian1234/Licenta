package com.licenta.services;

import com.licenta.dto.UserDTO;
import com.licenta.dto.convertor.UserToUserDTO;
import com.licenta.models.User;
import com.licenta.repositories.UserRepository;
import lombok.val;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Period;

@Service
public final class UserService {

    private final UserRepository userRepository;
    private final UserToUserDTO userDTOConverter;

    @Autowired
    public UserService(
            final UserRepository userRepository,
            final UserToUserDTO userDTOConverter
    ) {
        this.userRepository = userRepository;
        this.userDTOConverter = userDTOConverter;
    }


    public User findUserByEmail(final String email) {
        if (email == null) {
            return null;
        }
        return userRepository.findByEmailEquals(email);
    }

    public UserDTO findByEmail(final String email) {
        if (email == null) {
            return null;
        }
        final User user = userRepository.findByEmailEquals(email);
        return userDTOConverter.convert(user);
    }


    public User createUser(
            final UserDTO userDTO
    ) {
        double fat;
        double carbs;
        double protein;
        val gender = userDTO.getGender().equals(User.Gender.MALE) ? 5 : -161;
        double maintenanceCalories = (10 * userDTO.getDesiredWeight() + 6.25 * userDTO.getHeightInCm() - 5 * Period.between(userDTO.getBirthdate(), LocalDate.now()).getYears() + gender) * userDTO.getActivityLevel().getValue();
        switch (userDTO.getDiet()) {
            case "paleo":
                fat = 0.40;
                protein = 0.35;
                carbs = 0.25;
                break;
            case "keto":
                fat = 0.70;
                protein = 0.20;
                carbs = 0.10;
                break;
            case "vegan":
                fat = 0.40;
                protein = 0.30;
                carbs = 0.30;
                break;
            case "balanced":
                fat = 0.30;
                protein = 0.20;
                carbs = 0.50;
                break;
            default:
                fat = 0;
                protein = 0;
                carbs = 0;
        }
        final User user = new User(
                userDTO.getId() + 1,
                userDTO.getGender().toString(),
                userDTO.getBirthdate(),
                userDTO.getHeightInCm(),
                userDTO.getActivityLevel(),
                userDTO.getEmail(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                maintenanceCalories,
                userDTO.getWeight(),
                userDTO.getDesiredWeight(),
                userDTO.getCreatedAt(),
                userDTO.getDiet(),
                fat,
                carbs,
                protein,
                userDTO.getPassword()
        );
        return userRepository.save(user);
    }


    public void updateUser(final UserDTO userDTO) {
        updateUser(userDTO, null);
    }

    /**
     * TODO: Document
     * TODO: Require logout and re-login after changing the username (or password?)
     * TODO: Don't allow email changes at all when using an external identity provider (e.g. Google)
     * TODO: On second thought, maybe just don't allow email changes period?
     */
    public void updateUser(
            final UserDTO userDTO,
            final String newPassword
    ) {
        final User user = userRepository.findByEmailEquals(userDTO.getEmail());
        user.setGender(userDTO.getGender());
        user.setBirthdate(userDTO.getBirthdate());
        user.setHeightInCm(userDTO.getHeightInCm());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPasswordHash(encryptPassword(newPassword));
        }
        userRepository.save(user);
    }


    public boolean verifyPassword(
            final UserDTO userDTO,
            final String password
    ) {
        final User user = userRepository.findByEmailEquals(userDTO.getEmail());
        return BCrypt.checkpw(password, user.getPasswordHash());
    }


    private String encryptPassword(final String rawPassword) {
        final String salt = BCrypt.gensalt(10, new SecureRandom());
        return BCrypt.hashpw(rawPassword, salt);
    }


}
