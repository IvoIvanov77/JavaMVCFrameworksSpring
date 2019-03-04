package org.softuni.exercises.car_dealer.services;

import org.softuni.exercises.car_dealer.models.dtos.request.UserLoginBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.UserRegisterBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.responce.LoginUserDto;

public interface UserService {

    boolean register(UserRegisterBindingModel model);

    LoginUserDto login(UserLoginBindingModel model);


}
