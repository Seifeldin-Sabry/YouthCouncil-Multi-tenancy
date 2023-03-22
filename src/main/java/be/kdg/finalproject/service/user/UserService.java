package be.kdg.finalproject.service.user;

import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;

import java.util.List;

public interface UserService {
	User addRegularUser(UserSignUpViewModel userSignUpViewModel);
	User addYCAdmin(UserSignUpViewModel userSignUpViewModel);
	User addModerator(UserSignUpViewModel userSignUpViewModel);
	User addGA(UserSignUpViewModel userSignUpViewModel);
	List<User> getAllUsers();
	void processOAuthPostLoginGoogle(String email, String givenName, String familyName, Provider provider);
	void processOAuthPostLoginFaceBook(String email, String name, Provider provider);
	User getUserByID(long id);
	void deleteUser(User user);

	User getUserByUsernameOrEmail(String username);

	List<User> getUsersByRole(Role role);
}
