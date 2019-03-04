package org.softuni.exercises.car_dealer.repositories;

import org.softuni.exercises.car_dealer.models.entities.Customer;
import org.softuni.exercises.car_dealer.models.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByCustomer(Customer customer);
}
