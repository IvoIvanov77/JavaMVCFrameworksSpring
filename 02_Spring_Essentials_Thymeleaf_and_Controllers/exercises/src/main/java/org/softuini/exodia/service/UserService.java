package org.softuini.exodia.service;

import org.softuini.exodia.domain.models.binding.UserLoginBindingModel;
import org.softuini.exodia.domain.models.service.UserServiceModel;


public interface UserService {
    boolean registerUser(UserServiceModel serviceModel);

    boolean loginUser(UserLoginBindingModel loginModel);
}
