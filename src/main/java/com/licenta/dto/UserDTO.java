package com.licenta.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.licenta.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    @JsonIgnore
    private long id;
    private User.Gender gender;
    private LocalDate birthdate;
    private double heightInCm;
    private User.ActivityLevel activityLevel;
    private String email;
    private String firstName;
    private String lastName;
    private double maintenanceCalories;
    private double weight;
    private double desiredWeight;
    private LocalDate createdAt;
    private String diet;
    private double fat;
    private double carbs;
    private double protein;
    private String password;


    public UserDTO(User.Gender gender, LocalDate birthdate, double heightInCm, User.ActivityLevel activityLevel, String email, String firstName, String lastName, double maintenanceCalories, double weight, double desiredWeight, LocalDate localDate, String diet, double fat, double carbs, double protein, String password) {
        this.gender = gender;
        this.birthdate = birthdate;
        this.heightInCm = heightInCm;
        this.activityLevel = activityLevel;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maintenanceCalories = maintenanceCalories;
        this.weight = weight;
        this.desiredWeight = desiredWeight;
        this.createdAt = localDate;
        this.diet = diet;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
        this.password = password;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;

        UserDTO userDTO = (UserDTO) o;

        if (id != userDTO.id) return false;
        if (Double.compare(userDTO.heightInCm, heightInCm) != 0) return false;
        if (Double.compare(userDTO.maintenanceCalories, maintenanceCalories) != 0) return false;
        if (Double.compare(userDTO.weight, weight) != 0) return false;
        if (Double.compare(userDTO.desiredWeight, desiredWeight) != 0) return false;
        if (gender != userDTO.gender) return false;
        if (birthdate != null ? !birthdate.equals(userDTO.birthdate) : userDTO.birthdate != null) return false;
        if (activityLevel != userDTO.activityLevel) return false;
        if (email != null ? !email.equals(userDTO.email) : userDTO.email != null) return false;
        if (firstName != null ? !firstName.equals(userDTO.firstName) : userDTO.firstName != null) return false;
        return lastName != null ? lastName.equals(userDTO.lastName) : userDTO.lastName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        temp = Double.doubleToLongBits(heightInCm);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (activityLevel != null ? activityLevel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        temp = Double.doubleToLongBits(maintenanceCalories);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(desiredWeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
