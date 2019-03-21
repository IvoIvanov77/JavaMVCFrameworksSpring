package org.softuni.resident_evil.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.resident_evil.domain.models.binding.VirusAddBindingModel;
import org.softuni.resident_evil.domain.models.binding.VirusEditBindingModel;
import org.softuni.resident_evil.domain.models.service.VirusServiceModel;
import org.softuni.resident_evil.domain.models.view.VirusTableViewModel;
import org.softuni.resident_evil.service.CapitalService;
import org.softuni.resident_evil.service.VirusService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    private final VirusService virusService;
    private final CapitalService capitalService;
    private final ModelMapper mapper;


    public VirusController(ModelMapper mapper, VirusService virusService, CapitalService capitalService) {
        this.mapper = mapper;
        this.virusService = virusService;
        this.capitalService = capitalService;
    }

    @GetMapping("")
    public ModelAndView allVirusesView() {
        List<VirusTableViewModel> allViruses = this.virusService.getAllViruses();
        return view("/all-viruses")
                .addObject("allViruses", allViruses);

    }

    @GetMapping("/add")
    public ModelAndView addView(@ModelAttribute("model") VirusAddBindingModel model) {
        return prepareVirusModel("add-form", true);
    }

    @PostMapping("/add")
    public ModelAndView addAction(@Valid @ModelAttribute("model") VirusAddBindingModel model,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return prepareVirusModel("add-form", true);
        }
        VirusServiceModel serviceModel = this.mapper.map(model, VirusServiceModel.class);
        if (this.virusService.createVirus(serviceModel)) {
            return redirect("/");
        }
        return super.view("/add-form");
    }

    @GetMapping("/edit/{virusId}")
    public ModelAndView editView(@PathVariable String virusId) {
        VirusServiceModel serviceModel = this.virusService.getOneById(virusId);
        VirusEditBindingModel editBindingModel =
                this.mapper.map(serviceModel, VirusEditBindingModel.class);

        if (editBindingModel == null) {
            return super.redirect("/viruses");
        }

        return this.prepareVirusModel("edit-form",false)
                .addObject("model", editBindingModel);
    }

    @PostMapping("/edit")
    public ModelAndView editAction(@Valid @ModelAttribute("model") VirusEditBindingModel editBindingModel,
                                 final BindingResult bindingResult) {
        if (editBindingModel == null) {
            return super.redirect("/viruses");
        }

        if (bindingResult.hasErrors() || !this.virusService.editVirus(editBindingModel)) {
            return this.prepareVirusModel("edit-form", false)
                    .addObject("model", editBindingModel);
        }


        return super.redirect("/viruses");
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteAction(@PathVariable String id) {
        if (this.virusService.deleteVirus(id)){
            return this.redirect("/viruses");
        }
        return this.redirect("/viruses");
    }

    private ModelAndView prepareVirusModel(String formName, boolean showDate){
        return view(formName)
                .addObject("allCapitals",
                        this.capitalService.capitalsNamesList())
                .addObject("showDate", showDate);
    }

}
