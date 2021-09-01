package com.licenta.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "exercise_performed",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "exercise_id", "date"})
)
public class ExercisePerformed {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "minutes", nullable = false)
    private Integer minutes;

    public ExercisePerformed(
            final UUID id,
            final User user,
            final Exercise exercise,
            final Date date,
            final int minutes
    ) {
        this.id = Optional.ofNullable(id).orElse(UUID.randomUUID());
        this.user = user;
        this.exercise = exercise;
        this.date = (Date) date.clone();
        this.minutes = minutes;
    }


}

