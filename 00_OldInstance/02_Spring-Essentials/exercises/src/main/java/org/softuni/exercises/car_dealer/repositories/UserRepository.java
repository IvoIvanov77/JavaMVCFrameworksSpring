package org.softuni.exercises.car_dealer.repositories;

import org.softuni.exercises.car_dealer.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User getFirstByEmail(String email);

    Boolean existsByEmailOrUsername(String email, String username);
}
