package org.softuni.exercises.car_dealer.services;

import org.softuni.exercises.car_dealer.models.dtos.request.EditPartBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.PartBindingModel;
import org.softuni.exercises.car_dealer.models.entities.Part;

import java.util.List;

public interface PartService {

    List<Part> getAll();

    List<Part> getPartsByCarId(Long carId);

    boolean createPart(PartBindingModel partBindingModel);

    PartBindingModel getModelById(Long id);

    void deletePart(Long id);

    boolean editPart(EditPartBindingModel model, Long id);
}
