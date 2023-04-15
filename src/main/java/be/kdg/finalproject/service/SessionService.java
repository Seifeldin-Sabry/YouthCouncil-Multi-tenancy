package be.kdg.finalproject.service;

import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class SessionService {

	private final String CURRENT_MUNICIPALITY_ID = "currentMunicipalityId";
	private final String CURRENT_MEMBERSHIP = "currentMembership";
	private final String USER = "user";
	private boolean isUserInSession = false;
	private boolean isCredentialsChanged = false;

	public boolean isCredentialsChanged() {
		return isCredentialsChanged;
	}

	public void setCredentialsChanged(boolean credentialsChanged) {
		this.isCredentialsChanged = credentialsChanged;
	}

	public boolean isMunicipalityChanged(HttpSession session, Long municipalityId) {
		AtomicBoolean attributeExists = new AtomicBoolean(false);
		session.getAttributeNames().asIterator().forEachRemaining(s -> {
			if (s.equals(CURRENT_MUNICIPALITY_ID)) attributeExists.set(true);
		});
		return (session.getAttribute(CURRENT_MUNICIPALITY_ID) == null && attributeExists.get()) || (attributeExists.get() && (long) session.getAttribute(CURRENT_MUNICIPALITY_ID) != municipalityId);
	}

	public boolean isUserInSession() {
		return isUserInSession;
	}

	public void setUserInSession(boolean isUserInSession) {
		this.isUserInSession = isUserInSession;
		this.isCredentialsChanged = true;
	}

	public void setUserInSession(HttpSession session, User user) {
		session.setAttribute(USER, user);
	}

	public void setCurrentMunicipalityId(HttpSession session, Long currentMunicipalityId) {
		session.setAttribute(CURRENT_MUNICIPALITY_ID, currentMunicipalityId);
	}

	public void setCurrentMembership(HttpSession session, Membership membershipByUserAndMunicipalityName) {
		session.setAttribute(CURRENT_MEMBERSHIP, membershipByUserAndMunicipalityName);
	}

	public User getUser(HttpSession session) {
		return (User) session.getAttribute(USER);
	}

	public Membership getCurrentMembership(HttpSession session) {
		return (Membership) session.getAttribute(CURRENT_MEMBERSHIP);
	}
}
