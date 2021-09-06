package com.licenta.repositories;

import com.licenta.models.ExercisePerformed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExersicePerformedRepository extends JpaRepository<ExercisePerformed, Long> {
    List<ExercisePerformed> findByDateEqualsOrderByDate(LocalDate date);
}
