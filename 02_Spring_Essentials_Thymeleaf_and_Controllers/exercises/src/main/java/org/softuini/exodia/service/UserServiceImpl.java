package org.softuini.exodia.service;

import org.modelmapper.ModelMapper;
import org.softuini.exodia.domain.entities.User;
import org.softuini.exodia.domain.models.binding.UserLoginBindingModel;
import org.softuini.exodia.domain.models.service.UserServiceModel;
import org.softuini.exodia.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerUser(UserServiceModel serviceModel){
        User user = this.modelMapper.map(serviceModel, User.class);
        try {
            this.userRepository.saveAndFlush(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean loginUser(UserLoginBindingModel loginModel){
        User user = this.userRepository.getUserByUsername(loginModel.getUsername());
        if(user == null || !user.getPassword().equals(loginModel.getPassword())){
            return false;
        }
        return true;
    }
}
