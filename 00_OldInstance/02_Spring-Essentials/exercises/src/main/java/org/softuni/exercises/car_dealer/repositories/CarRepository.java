package org.softuni.exercises.car_dealer.repositories;

import org.softuni.exercises.car_dealer.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByMake(String make);
}
