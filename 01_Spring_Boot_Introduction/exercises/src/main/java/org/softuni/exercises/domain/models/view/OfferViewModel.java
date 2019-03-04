package org.softuni.exercises.domain.models.view;

import org.softuni.exercises.constants.StringConstants;

import java.math.BigDecimal;

public class OfferViewModel {

    private BigDecimal apartmentRent;

    private String apartmentType;

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

    @Override
    public String toString() {
        return String.format(StringConstants.APARTMENT_TEMPLATE,
                this.apartmentRent,
                this.apartmentType,
                this.agencyCommission);
    }
}
