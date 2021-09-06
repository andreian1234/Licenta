package com.licenta.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(
        name = "exercise_performed",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "exercise_id", "date"})
)
public class ExercisePerformed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "minutes", nullable = false)
    private Integer minutes;

    @Column(name = "calories_burned")
    private int caloriesBurned;

    public ExercisePerformed(
            final Long id,
            final User user,
            final Exercise exercise,
            final LocalDate date,
            final int minutes,
            final int caloriesBurned
    ) {
        this.id = id;
        this.user = user;
        this.exercise = exercise;
        this.date = date;
        this.minutes = minutes;
        this.caloriesBurned = caloriesBurned;
    }

    public ExercisePerformed(
            final User user,
            final Exercise exercise,
            final LocalDate date,
            final int minutes,
            final int caloriesBurned

    ) {
        this.user = user;
        this.exercise = exercise;
        this.date = date;
        this.minutes = minutes;
        this.caloriesBurned = caloriesBurned;
    }

    public ExercisePerformed() {
        this.caloriesBurned = 0;
        this.minutes = 0;
        this.date = null;
        this.exercise = null;
        this.user = null;
    }


}

