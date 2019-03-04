package org.softuni.exercises.car_dealer.services;

import org.softuni.exercises.car_dealer.models.dtos.request.UserLoginBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.UserRegisterBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.responce.LoginUserDto;
import org.softuni.exercises.car_dealer.models.entities.User;
import org.softuni.exercises.car_dealer.repositories.UserRepository;
import org.softuni.exercises.car_dealer.util.DtoMapper;
import org.softuni.exercises.car_dealer.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean validateRegisterForm(UserRegisterBindingModel model){
        if(this.userRepository.existsByEmailOrUsername(model.getEmail(), model.getUsername())){
            return false;
        }
        if(!model.getConfirmPassword().equals(model.getPassword())){
            return false;
        }
        if(!Validator.validateEmail(model.getEmail())){
            return false;
        }
        if(!Validator.validatePassword(model.getPassword())){
            return false;
        }if(!Validator.validateUsermame(model.getUsername())){
            return false;
        }
        return true;
    }

    @Override
    public boolean register(UserRegisterBindingModel model) {
        if(!this.validateRegisterForm(model)){
            return false;
        }
        User user = DtoMapper.convert(model, User.class);
        return this.userRepository.saveAndFlush(user) != null;
    }

    @Override
    public LoginUserDto login(UserLoginBindingModel model) {
        User user = this.userRepository.getFirstByEmail(model.getEmail());
        if(user != null && model.getPassword().equals(user.getPassword())){
            return DtoMapper.convert(user, LoginUserDto.class);
        }
        return null;
    }
}
