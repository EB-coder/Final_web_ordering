package com.example.demo.Repos;

import com.example.demo.Entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrinkRepo extends JpaRepository<Drink, Integer> {
    Optional<Drink> findById(Integer id);
}
