package be.kdg.finalproject.service.membership;

import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;

public interface MembershipService {
	Membership addMembershipByUserAndPostCode(User user, Integer postCode);
}