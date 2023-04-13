package be.kdg.finalproject.service;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class SessionService {

	private final String IS_CREDENTIALS_CHANGED = "isCredentialsChanged";
	private final String CURRENT_MUNICIPALITY_ID = "currentMunicipalityId";
	private final String CURRENT_MUNICIPALITY = "currentMunicipality";
	private final String CURRENT_MEMBERSHIP = "currentMembership";
	private final String USER = "user";

	public boolean isCredentialsChanged(HttpSession session) {
		return session.getAttribute(IS_CREDENTIALS_CHANGED) != null;
	}

	public boolean isMunicipalityChanged(HttpSession session, Long municipalityId) {
		AtomicBoolean attributeExists = new AtomicBoolean(false);
		session.getAttributeNames().asIterator().forEachRemaining(s -> {
			if (s.equals(CURRENT_MUNICIPALITY_ID)) attributeExists.set(true);
		});
		return (session.getAttribute(CURRENT_MUNICIPALITY_ID) == null && attributeExists.get()) || (attributeExists.get() && (long) session.getAttribute(CURRENT_MUNICIPALITY_ID) != municipalityId);
	}

	public boolean isUserInSession(HttpSession session) {
		return session.getAttribute(USER) != null;
	}

	public void setCredentialsChanged(boolean credentialsChanged, HttpSession session) {
		session.setAttribute(IS_CREDENTIALS_CHANGED, credentialsChanged);
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

	public void setCurrentMunicipality(HttpSession session, Municipality municipality) {
		session.setAttribute(CURRENT_MUNICIPALITY, municipality);
	}

	public Municipality getCurrentMunicipality(HttpSession session) {
		return (Municipality) session.getAttribute(CURRENT_MUNICIPALITY);
	}
}
