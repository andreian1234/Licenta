package com.licenta.repositories;

import com.licenta.models.RecipeEaten;
import com.licenta.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecipeEatenRepository extends JpaRepository<RecipeEaten, Long> {

    List<RecipeEaten> findByUserEqualsAndDateEquals(User user, LocalDate date);
}
