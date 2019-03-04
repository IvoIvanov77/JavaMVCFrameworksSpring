package org.softuni.exercises.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.exercises.constants.StringConstants;
import org.softuni.exercises.domain.models.service.OfferServiceModel;
import org.softuni.exercises.domain.models.view.OfferViewModel;
import org.softuni.exercises.services.OfferService;
import org.softuni.exercises.util.HtmlRider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    private final OfferService offerService;

    private final HtmlRider htmlRider;

    private final ModelMapper modelMapper;

    public HomeController(OfferService offerService, HtmlRider htmlRider, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.htmlRider = htmlRider;
        this.modelMapper = modelMapper;
    }

    private String listAllOffersAsHtml(){
        StringBuilder result = new StringBuilder();
        List<OfferServiceModel> allOffers = this.offerService.getAllOffers();
        if(allOffers.isEmpty()){
            return StringConstants.THERE_ARE_NO_OFFERS_TEMPLATE;
        }
        allOffers.stream()
                .map(serviceModel -> this.modelMapper.map(serviceModel, OfferViewModel.class))
                .forEach(offerViewModel -> result.append(offerViewModel.toString())
                        .append(System.lineSeparator()));
        return result.toString();
    }

    @GetMapping("/")
    @ResponseBody
    public String getHomePage() throws IOException {
        String htmlContent = this.htmlRider.readHtmlFile(StringConstants.INDEX_HTML_PATH);
        return String.format(htmlContent, this.listAllOffersAsHtml());
    }
}
