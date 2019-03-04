package org.softuni.exercises.car_dealer.controllers;

import org.softuni.exercises.car_dealer.models.dtos.request.EditPartBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.PartBindingModel;
import org.softuni.exercises.car_dealer.services.PartService;
import org.softuni.exercises.car_dealer.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/parts")
public class PartController extends BaseController{

    private final PartService partService;

    private final SupplierService supplierService;

    @Autowired
    public PartController(PartService partService, SupplierService supplierService) {
        this.partService = partService;
        this.supplierService = supplierService;
    }

    @GetMapping("/all")
    public ModelAndView getAllParts(){
        return render("parts/parts_list", "All Parts")
                .addObject("parts", this.partService.getAll());

    }

    @GetMapping("/create")
    public ModelAndView createPartView(){
        PartBindingModel partBindingModel = new PartBindingModel();
        partBindingModel.setQuantity(1);
        return render("forms/add_part", "Add Part")
                .addObject("model", partBindingModel)
                .addObject("suppliersList", this.supplierService.allSuppliersList());
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCarView(@PathVariable Long id){
        return render("forms/edit_part", "Edit Part")
                .addObject("model", this.partService.getModelById(id))
                .addObject("id", id);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCarView(@PathVariable Long id){
        return render("forms/delete_part", "Delete Part")
                .addObject("model", this.partService.getModelById(id))
                .addObject("id", id);
    }

    @PostMapping("/create")
    public ModelAndView createPartAction(PartBindingModel model){
        if(this.partService.createPart(model)){
            return this.redirect("/parts/all");
        }
        return this.redirect("/parts/create");
    }

    @PostMapping("/delete/{id}")
    public ModelAndView createPartAction(@PathVariable Long id){
        this.partService.deletePart(id);
        return redirect("/parts/all");
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editCustomer(EditPartBindingModel model, @PathVariable("id")Long id){

        boolean actionResult = this.partService.editPart(model, id);
        if(!actionResult){
            return null;
        }
        return this.redirect("/parts/all");
    }
}
