package be.kdg.finalproject.service.user;

import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.user.User;

public interface UserService {
	User addRegularUser(UserSignUpViewModel userSignUpViewModel);
	void processOAuthPostLoginGoogle(String email, String givenName, String familyName, Provider provider);
	void processOAuthPostLoginFaceBook(String email, String name, Provider provider);
	User getUserByID(long id);

	User getUserByUsernameOrEmail(String username);
}
