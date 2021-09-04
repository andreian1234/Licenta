package com.licenta.services;

import com.licenta.dto.UserDTO;
import com.licenta.dto.WeightDTO;
import com.licenta.dto.convertor.UserToUserDTO;
import com.licenta.dto.convertor.WeightToWeightDTO;
import com.licenta.models.User;
import com.licenta.models.Weight;
import com.licenta.repositories.UserRepository;
import com.licenta.repositories.WeightRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

@Service
public final class UserService {

    private final UserRepository userRepository;
    private final WeightRepository weightRepository;
    private final UserToUserDTO userDTOConverter;
    private final WeightToWeightDTO weightDTOConverter;

    @Autowired
    public UserService(
            final UserRepository userRepository,
            final WeightRepository weightRepository,
            final UserToUserDTO userDTOConverter,
            final WeightToWeightDTO weightDTOConverter
    ) {
        this.userRepository = userRepository;
        this.weightRepository = weightRepository;
        this.userDTOConverter = userDTOConverter;
        this.weightDTOConverter = weightDTOConverter;
    }


    public UserDTO findByEmail(final String email) {
        if (email == null) {
            return null;
        }
        final User user = userRepository.findByEmailEquals(email);
        return userDTOConverter.convert(user);
    }

    public void createUser(
            final UserDTO userDTO,
            final String password
    ) {
        final User user = new User(
                userDTO.getId(),
                userDTO.getGender(),
                userDTO.getBirthdate(),
                userDTO.getHeightInCm(),
                userDTO.getActivityLevel(),
                userDTO.getEmail(),
                encryptPassword(password),
                userDTO.getFirstName(),
                userDTO.getLastName()
        );
        userRepository.save(user);
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


    public void updateWeight(
            final UserDTO userDTO,
            final Date date,
            final double pounds
    ) {
        final User user = userRepository.findByEmailEquals(userDTO.getEmail());
        Weight weight = weightRepository.findByUserAndDate(user, date);
        if (weight == null) {
            weight = new Weight(
                    new Random().nextLong(),
                    user,
                    date,
                    pounds
            );
        } else {
            weight.setPounds(pounds);
        }
        weightRepository.save(weight);
    }

    public WeightDTO findWeightOnDate(
            final UserDTO userDTO,
            final Date date
    ) {
        final User user = userRepository.findByEmailEquals(userDTO.getEmail());
        final Weight weight = weightRepository.findByUserMostRecentOnDate(user, date);
        return weightDTOConverter.convert(weight);
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
