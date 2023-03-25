package be.kdg.finalproject.service.user;

import be.kdg.finalproject.controller.api.dto.patch.UpdatedUserDTO;
import be.kdg.finalproject.controller.api.dto.post.NewUserDto;
import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;

import java.util.List;
import java.util.UUID;

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

	User updateUser(Long userId, UpdatedUserDTO updatedUserDTO);

	void deleteAccount(long userId);

	List<User> getUsersByRole(Role role);

	void addMemberToMunicipality(UUID uuid, NewUserDto user);

	boolean newEmailIsCurrentEmail(User user, String email);

	boolean newUsernameIsCurrentUsername(User user, String username);

	boolean emailExists(String email);

	boolean usernameExists(String username);
}
