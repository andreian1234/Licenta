package com.licenta.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FITNESSJIFFY_USER")
public class User {

    @Id
    @Column(name = "ID")
    private UUID id;
    @Column(name = "GENDER", length = 6, nullable = false)
    private String gender;
    @Column(name = "BIRTHDATE", nullable = false)
    private Date birthdate;
    @Column(name = "HEIGHT_IN_CENTIMITERS", nullable = false)
    private double heightInInches;
    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;
    @Column(name = "PASSWORD_HASH", length = 100)
    private String passwordHash;
    @Column(name = "FIRST_NAME", length = 20, nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", length = 20, nullable = false)
    private String lastName;
    @OneToMany(mappedBy = "user")
    private Set<Weight> weights = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<FoodEaten> foodsEaten = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<RecipeEaten> recipesEaten = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<ExercisePerformed> exercisesPerformed = new HashSet<>();

    public User(
            final UUID id,
            final Gender gender,
            final Date birthdate,
            final double heightInInches,
            final String email,
            final String passwordHash,
            final String firstName,
            final String lastName
    ) {
        this.id = Optional.ofNullable(id).orElse(UUID.randomUUID());
        this.gender = gender.toString();
        this.birthdate = (Date) birthdate.clone();
        this.heightInInches = heightInInches;
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (Double.compare(user.heightInInches, heightInInches) != 0) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
        if (birthdate != null ? !birthdate.equals(user.birthdate) : user.birthdate != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (passwordHash != null ? !passwordHash.equals(user.passwordHash) : user.passwordHash != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        return lastName != null ? lastName.equals(user.lastName) : user.lastName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        temp = Double.doubleToLongBits(heightInInches);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
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

}