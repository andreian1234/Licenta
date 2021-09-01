package com.licenta.repositories;

import com.licenta.models.RecipeEaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipeEatenRepository extends JpaRepository<RecipeEaten, UUID> {
}
