package be.kdg.finalproject.service.user;

import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.user.User;

public interface UserService {
	User addRegularUser(UserSignUpViewModel userSignUpViewModel);
}
