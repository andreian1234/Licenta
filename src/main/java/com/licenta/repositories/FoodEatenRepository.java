package com.licenta.repositories;

import com.licenta.models.FoodEaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodEatenRepository extends JpaRepository<FoodEaten, Long> {
}
