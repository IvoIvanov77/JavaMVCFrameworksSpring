package org.softuni.exercises.domain.models.binding;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class RegisterOfferBindingModel {

    @NotNull
    @Positive
    private BigDecimal apartmentRent;

    @NotNull
    @NotBlank
    private String apartmentType;

    @NotNull
    @Min(0)@Max(100)
    private BigDecimal agencyCommission;

    public BigDecimal getApartmentRent() {
        return apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }
}
