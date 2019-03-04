package org.softuni.exercises.car_dealer.repositories;

import org.softuni.exercises.car_dealer.models.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface PartRepository extends JpaRepository<Part, Long> {

    @Query("select p from Part p inner join p.cars as c where c.id = :id")
    List<Part> findAllByCarId(@Param("id")Long carId);

    @Query("select p from Part p where p.id in :ids")
    Set<Part> findByIds(List<Long> ids);
}
