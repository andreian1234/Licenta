//package com.licenta.dto;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//import java.sql.Date;
//import java.util.UUID;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserDTO implements Serializable {
//
//    @JsonIgnore
//    private UUID id;
//    private User.Gender gender;
//    private Date birthdate;
//    private double heightInCm;
//    private User.ActivityLevel activityLevel;
//    private String email;
//    private String firstName;
//    private String lastName;
//    private String timeZone;
//    private double currentWeight;
//    private double bmi;
//    private int maintenanceCalories;
//
//    @Override
//    public boolean equals(Object o) {
//
//        if (this == o) return true;
//        if (!(o instanceof UserDTO)) return false;
//
//        UserDTO userDTO = (UserDTO) o;
//
//        if (Double.compare(userDTO.heightInCm, heightInCm) != 0) return false;
//        if (Double.compare(userDTO.currentWeight, currentWeight) != 0) return false;
//        if (Double.compare(userDTO.bmi, bmi) != 0) return false;
//        if (maintenanceCalories != userDTO.maintenanceCalories) return false;
//        if (id != null ? !id.equals(userDTO.id) : userDTO.id != null) return false;
//        if (gender != null ? !gender.equals(userDTO.gender) : userDTO.gender != null) return false;
//        if (birthdate != null ? !birthdate.equals(userDTO.birthdate) : userDTO.birthdate != null) return false;
//        if (activityLevel != null ? !activityLevel.equals(userDTO.activityLevel) : userDTO.activityLevel != null)
//            return false;
//        if (email != null ? !email.equals(userDTO.email) : userDTO.email != null) return false;
//        if (firstName != null ? !firstName.equals(userDTO.firstName) : userDTO.firstName != null) return false;
//        if (lastName != null ? !lastName.equals(userDTO.lastName) : userDTO.lastName != null) return false;
//        return timeZone != null ? timeZone.equals(userDTO.timeZone) : userDTO.timeZone == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (gender != null ? gender.hashCode() : 0);
//        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
//        temp = Double.doubleToLongBits(heightInCm);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        result = 31 * result + (activityLevel != null ? activityLevel.hashCode() : 0);
//        result = 31 * result + (email != null ? email.hashCode() : 0);
//        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
//        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
//        result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
//        temp = Double.doubleToLongBits(currentWeight);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(bmi);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        result = 31 * result + maintenanceCalories;
//        return result;
//    }
//}
