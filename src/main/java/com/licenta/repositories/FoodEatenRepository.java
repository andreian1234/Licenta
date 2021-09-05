package com.licenta.repositories;

import com.licenta.models.FoodEaten;
import com.licenta.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoodEatenRepository extends JpaRepository<FoodEaten, Long> {
    List<FoodEaten> findByUserEqualsAndDateEquals(User user, LocalDate localDate);

//    List<FoodEaten> findAll();

}
