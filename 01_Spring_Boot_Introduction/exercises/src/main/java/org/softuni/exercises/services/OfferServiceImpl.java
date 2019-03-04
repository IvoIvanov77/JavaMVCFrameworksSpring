package org.softuni.exercises.services;

import org.softuni.exercises.domain.entities.Offer;
import org.softuni.exercises.domain.models.binding.FindOfferBindingModel;
import org.softuni.exercises.domain.models.binding.RegisterOfferBindingModel;
import org.softuni.exercises.domain.models.service.OfferServiceModel;
import org.softuni.exercises.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    private final Validator validator;

    public OfferServiceImpl(OfferRepository offerRepository, Validator validator) {
        this.offerRepository = offerRepository;
        this.validator = validator;
    }

    @Override
    public List<OfferServiceModel> getAllOffers(){
        return this.offerRepository.findAll()
                .stream()
                .map(offer -> {
                    OfferServiceModel serviceModel = new OfferServiceModel();
                    serviceModel.setId(offer.getId());
                    serviceModel.setApartmentRent(offer.getApartmentRent());
                    serviceModel.setApartmentType(offer.getApartmentType());
                    serviceModel.setAgencyCommission(offer.getAgencyCommission());
                    return serviceModel;
                }).collect(Collectors.toList());
    }

    @Override
    public boolean registerOffer(RegisterOfferBindingModel bindingModel){
        if(this.validator.validate(bindingModel).size() > 0){
            return false;
        }
        Offer offer = new Offer();
        offer.setApartmentRent(bindingModel.getApartmentRent());
        offer.setApartmentType(bindingModel.getApartmentType());
        offer.setAgencyCommission(bindingModel.getAgencyCommission());
        return this.offerRepository.saveAndFlush(offer) != null;
    }

    @Override
    public boolean findOffer(FindOfferBindingModel findOfferModel){
        if(this.validator.validate(findOfferModel).size() > 0){
            return false;
        }
        Offer offer = this.offerRepository.findOneByTypeAndTotalPrice(findOfferModel.getApartmentType(),
                findOfferModel.getFamilyBudget());
        if(offer == null){
            return false;
        }

        this.offerRepository.delete(offer);
        return true;
    }


}
