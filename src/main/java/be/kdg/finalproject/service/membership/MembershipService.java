package be.kdg.finalproject.service.membership;

import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;

import java.util.List;

public interface MembershipService {
	Membership addMembershipByUserAndPostCode(User user, Integer postCode, Role role);
	List<Membership> getAllMembershipsByUser(User user);
}
