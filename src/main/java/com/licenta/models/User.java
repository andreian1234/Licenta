package com.licenta.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "Application_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "GENDER", length = 6, nullable = false)
    private String gender;
    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate birthdate;
    @Column(name = "HEIGHT_IN_CENTIMITERS", nullable = false)
    private double heightInCm;
    @Column(name = "ACTIVITY_LEVEL", nullable = false)
    private double activityLevel;
    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;
    @Column(name = "PASSWORD_HASH", length = 100)
    private String passwordHash;
    @Column(name = "FIRST_NAME", length = 20, nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", length = 20, nullable = false)
    private String lastName;
    @Column(name = "MAINTENANANCE_CALORIES")
    private double maintenanceCalories;
    @Column(name = "WEIGHT")
    private double weight;
    @Column(name = "DESIRED_WEIGHT")
    private double desiredWeight;
    @Column(name = "CREATED_AT")
    private LocalDate createdAt;
    @Column(name = "DIET")
    private String diet;
    @Column(name = "FAT")
    private double fat;
    @Column(name = "CARBS")
    private double carbs;
    @Column(name = "PROTEIN")
    private double protein;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private Set<FoodEaten> foodsEaten = new HashSet<>();
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<RecipeEaten> recipesEaten = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<ExercisePerformed> exercisesPerformed = new HashSet<>();

    public User(
            final long id,
            final String gender,
            final LocalDate birthdate,
            final double heightInCm,
            final ActivityLevel activityLevel,
            final String email,
            final String firstName,
            final String lastName,
            final double maintenanceCalories,
            final double weight,
            final double desiredWeight,
            final LocalDate createdAt,
            final String diet,
            final double fat,
            final double carbs,
            final double protein,
            final String password
    ) {
        this.id = id;
        this.gender = gender;
        this.birthdate = birthdate;
        this.heightInCm = heightInCm;
        this.activityLevel = activityLevel.getValue();
        this.email = email;
        this.passwordHash = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maintenanceCalories = maintenanceCalories;
        this.weight = weight;
        this.desiredWeight = desiredWeight;
        this.createdAt = createdAt;
        this.diet = diet;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(long protein) {
        this.protein = protein;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public double getDesiredWeight() {
        return desiredWeight;
    }

    public void setDesiredWeight(double desiredWeight) {
        this.desiredWeight = desiredWeight;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setActivityLevel(double activityLevel) {
        this.activityLevel = activityLevel;
    }

    public double getMaintenanceCalories() {
        return maintenanceCalories;
    }

    public void setMaintenanceCalories(double maintenanceCalories) {
        this.maintenanceCalories = maintenanceCalories;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Set<RecipeEaten> getRecipesEaten() {
        return recipesEaten;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(final LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public double getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(final double heightInCm) {
        this.heightInCm = heightInCm;
    }


    public ActivityLevel getActivityLevel() {
        return ActivityLevel.fromValue(activityLevel);
    }

    public void setActivityLevel(final ActivityLevel activityLevel) {
        this.activityLevel = activityLevel.getValue();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }


    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(final String passwordHash) {
        this.passwordHash = passwordHash;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }


    public Set<FoodEaten> getFoodsEaten() {
        return foodsEaten;
    }

    public void setFoodsEaten(final Set<FoodEaten> foodsEaten) {
        this.foodsEaten = foodsEaten;
    }


    public Set<ExercisePerformed> getExercisesPerformed() {
        return exercisesPerformed;
    }

    public void setExercisesPerformed(final Set<ExercisePerformed> exercisesPerformed) {
        this.exercisesPerformed = exercisesPerformed;
    }

    public void setRecipesEaten(Set<RecipeEaten> recipesEaten) {
        this.recipesEaten = recipesEaten;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (Double.compare(user.heightInCm, heightInCm) != 0) return false;
        if (Double.compare(user.activityLevel, activityLevel) != 0) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
        if (birthdate != null ? !birthdate.equals(user.birthdate) : user.birthdate != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (passwordHash != null ? !passwordHash.equals(user.passwordHash) : user.passwordHash != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (foodsEaten != null ? !foodsEaten.equals(user.foodsEaten) : user.foodsEaten != null) return false;
        if (recipesEaten != null ? !recipesEaten.equals(user.recipesEaten) : user.recipesEaten != null) return false;
        return exercisesPerformed != null ? exercisesPerformed.equals(user.exercisesPerformed) : user.exercisesPerformed == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        temp = Double.doubleToLongBits(heightInCm);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(activityLevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (foodsEaten != null ? foodsEaten.hashCode() : 0);
        result = 31 * result + (recipesEaten != null ? recipesEaten.hashCode() : 0);
        result = 31 * result + (exercisesPerformed != null ? exercisesPerformed.hashCode() : 0);
        return result;
    }


    public enum Gender {

        MALE, FEMALE;


        public static Gender fromString(final String s) {
            Gender match = null;
            for (final Gender gender : Gender.values()) {
                if (gender.toString().equalsIgnoreCase(s)) {
                    match = gender;
                }
            }
            return match;
        }

        @Override
        public String toString() {
            return super.toString();
        }

    }

    public Gender getGender() {
        return Gender.fromString(gender);
    }

    public void setGender(final Gender gender) {
        this.gender = gender.toString();
    }

    public enum ActivityLevel {

        SEDENTARY(1.2), LIGHTLY_ACTIVE(1.375), MODERATELY_ACTIVE(1.55), VERY_ACTIVE(1.725), EXTREMELY_ACTIVE(1.9);

        private double value;

        ActivityLevel(final double value) {
            this.value = value;
        }


        public static ActivityLevel fromValue(final double value) {
            ActivityLevel match = null;
            for (final ActivityLevel activityLevel : ActivityLevel.values()) {
                if (activityLevel.getValue() == value) {
                    match = activityLevel;
                }
            }
            return match;
        }


        public static ActivityLevel fromString(final String s) {
            ActivityLevel match = null;
            for (final ActivityLevel activityLevel : ActivityLevel.values()) {
                if (activityLevel.toString().equalsIgnoreCase(s)) {
                    match = activityLevel;
                }
            }
            return match;
        }

        public double getValue() {
            return this.value;
        }


    }
}