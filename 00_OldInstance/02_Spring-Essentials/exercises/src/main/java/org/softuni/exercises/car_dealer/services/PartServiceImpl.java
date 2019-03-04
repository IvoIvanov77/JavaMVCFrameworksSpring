package org.softuni.exercises.car_dealer.services;

import org.softuni.exercises.car_dealer.models.dtos.request.EditPartBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.PartBindingModel;
import org.softuni.exercises.car_dealer.models.entities.Part;
import org.softuni.exercises.car_dealer.models.entities.Supplier;
import org.softuni.exercises.car_dealer.repositories.PartRepository;
import org.softuni.exercises.car_dealer.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Part> getAll(){
        return this.partRepository.findAll();
    }

    @Override
    public List<Part> getPartsByCarId(Long carId){
        return this.partRepository.findAllByCarId(carId);
    }

    @Override
    public boolean createPart(PartBindingModel partBindingModel){
        Supplier supplier = this.supplierRepository.getOne(partBindingModel.getSupplierId());

        Part part = new Part();
        part.setName(partBindingModel.getName());
        part.setPrice(partBindingModel.getPrice());
        part.setQuantity(partBindingModel.getQuantity());
        part.setSupplier(supplier);

        return this.partRepository.saveAndFlush(part) != null;
    }

    @Override
    public PartBindingModel getModelById(Long id){
        Part part = this.partRepository.getOne(id);
        PartBindingModel partBindingModel = new PartBindingModel();
        partBindingModel.setName(part.getName());
        partBindingModel.setPrice(part.getPrice());
        partBindingModel.setSupplierId(part.getSupplier().getId());
        partBindingModel.setSupplierName(part.getSupplier().getName());
        partBindingModel.setQuantity(part.getQuantity());
        return partBindingModel;
    }



    @Override
    public void deletePart(Long id){
        this.partRepository.deleteById(id);
    }

    @Override
    public boolean editPart(EditPartBindingModel model, Long id) {
        Part part = this.partRepository.getOne(id);
        part.setPrice(model.getPrice());
        part.setQuantity(model.getQuantity());
        return this.partRepository.saveAndFlush(part) != null;
    }

}
