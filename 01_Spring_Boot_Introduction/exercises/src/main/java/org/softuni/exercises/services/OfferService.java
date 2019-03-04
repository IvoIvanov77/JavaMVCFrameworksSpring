package org.softuni.exercises.services;

import org.softuni.exercises.domain.models.binding.FindOfferBindingModel;
import org.softuni.exercises.domain.models.binding.RegisterOfferBindingModel;
import org.softuni.exercises.domain.models.service.OfferServiceModel;

import java.util.List;

public interface OfferService {
    List<OfferServiceModel> getAllOffers();

    boolean registerOffer(RegisterOfferBindingModel bindingModel);

    boolean findOffer(FindOfferBindingModel findOfferModel);
}
