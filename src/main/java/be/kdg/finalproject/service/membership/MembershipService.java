package be.kdg.finalproject.service.membership;

import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;

import java.util.List;
import java.util.UUID;

public interface MembershipService {
	Membership addMembershipByUserAndPostCode(User user, Integer postCode, Role role);

	List<Membership> getAllMembershipsByUser(User user);

	void addMembershipByUserAndUuid(User newUser, UUID uuid, Role user);

	Membership getMembershipByUserAndMunicipalityName(User user, String municipalityName);
}
