package com.licenta.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "weight",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "date"})
)
public final class Weight {

    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "pounds", nullable = false)
    private Double pounds;

    public Weight(
            final long id,
            final User user,
            final java.util.Date date,
            final double pounds
    ) {
        this.id = id;
        this.user = user;
        this.date = (Date) date.clone();
        this.pounds = pounds;
    }
}