package org.softuni.exercises.car_dealer.services;

import org.softuni.exercises.car_dealer.models.dtos.request.SupplierBindingModel;
import org.softuni.exercises.car_dealer.models.entities.Supplier;
import org.softuni.exercises.car_dealer.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllByIsImporter(boolean isImporter){
        return this.supplierRepository.findAllByIsImporter(isImporter);
    }

    @Override
    public List<SupplierBindingModel> allSuppliersList() {
        return this.supplierRepository.findAll()
                .stream()
                .map(SupplierBindingModel::new)
                .collect(Collectors.toList());
    }

}
