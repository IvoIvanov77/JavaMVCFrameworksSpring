package org.softuni.resident_evil.domain.models.service;

import org.hibernate.validator.constraints.Length;
import org.softuni.resident_evil.domain.enums.Magnitude;
import org.softuni.resident_evil.domain.enums.Mutation;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

public class VirusServiceModel {
    private String id;

    private String name;

    private String description;

    private String sideEffects;

    private String creator;

    private Boolean isDeadly;

    private Boolean isCurable;

    private Mutation mutation;

    private Integer turnoverRate;

    private Integer hoursUntilMutation;

    private Magnitude magnitude;

    private LocalDate releasedOn;

    private Set<Long> capitals;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotEmpty
    @NotNull
    @Length(min = 3, max = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    @NotNull
    @Length(min = 5, max = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Length(max = 50)
    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @Pattern(regexp = "^corp|Corp$")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getDeadly() {
        return isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getCurable() {
        return isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    @NotNull
    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @Min(0)
    @Max(100)
    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @Min(1)
    @Max(12)
    public Integer getHoursUntilMutation() {
        return hoursUntilMutation;
    }

    public void setHoursUntilMutation(Integer hoursUntilMutation) {
        this.hoursUntilMutation = hoursUntilMutation;
    }

    @NotNull
    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    @Past
    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    public Set<Long> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<Long> capitals) {
        this.capitals = capitals;
    }

    //    o	Name – Cannot be empty, should be between 3 and 10 symbols.
//    o	Description – Cannot be empty, should be between 5 and 100 symbols.
//	Represented as Text in the database
//    o	Side Effects – Should have a maximum of 50 symbols.
//    o	Creator – Should be either Corp or corp.
//    o	Is Deadly – Boolean
//    o	Is Curable – Boolean
//    o	Mutation – Cannot be null. Should hold one of the following values:
//            	ZOMBIE
//	T_078_TYRANT
//	GIANT_SPIDER
//    o	Turnover Rate – Number, between 0 and 100.
//    o	Hours Until Turn (to a mutation) – Number, between 1 and 12.
//    o	Magnitude – Cannot be null. Should hold one of the following values:
//            	Low
//	Medium
//	High
//    o	Released On – Date, should be before the “today” date.
//    o	Capitals – A collection of Capitals.
//            •	Capitals
//    o	Name
//    o	Latitude
//    o	Longitude

}
