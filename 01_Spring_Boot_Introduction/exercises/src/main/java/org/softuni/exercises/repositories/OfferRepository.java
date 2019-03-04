package org.softuni.exercises.repositories;

import org.softuni.exercises.domain.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {

    @Query("select o from Offer o where o.apartmentType = :t and " +
            "(o.apartmentRent + o.apartmentRent*o.agencyCommission/100) <= :price")
    Offer findOneByTypeAndTotalPrice(@Param("t") String type, @Param("price") BigDecimal price);

}
