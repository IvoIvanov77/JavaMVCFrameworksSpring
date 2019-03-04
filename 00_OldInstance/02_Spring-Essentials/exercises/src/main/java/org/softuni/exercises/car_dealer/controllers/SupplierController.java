package org.softuni.exercises.car_dealer.controllers;

import org.softuni.exercises.car_dealer.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/suppliers")
public class SupplierController extends BaseController{

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/local")
    public ModelAndView getLocalSuppliers(){
        return render("suppliers/list", "Local Suppliers")
                .addObject("suppliers",
                        this.supplierService.getAllByIsImporter(false));
    }

    @GetMapping("/importers")
    public ModelAndView getImporterSuppliers(){
        return render("suppliers/list", "Importer Suppliers")
                .addObject("suppliers",
                        this.supplierService.getAllByIsImporter(true));
    }
}
