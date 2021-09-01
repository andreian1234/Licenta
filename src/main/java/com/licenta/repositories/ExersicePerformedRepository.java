package com.licenta.repositories;

import com.licenta.models.ExercisePerformed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExersicePerformedRepository extends JpaRepository<ExercisePerformed, UUID> {
}
