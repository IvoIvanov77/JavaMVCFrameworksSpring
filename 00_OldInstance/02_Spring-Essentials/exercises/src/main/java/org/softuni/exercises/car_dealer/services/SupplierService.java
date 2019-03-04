package org.softuni.exercises.car_dealer.services;

import org.softuni.exercises.car_dealer.models.dtos.request.SupplierBindingModel;
import org.softuni.exercises.car_dealer.models.entities.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAllByIsImporter(boolean isImporter);

    List<SupplierBindingModel> allSuppliersList();
}
