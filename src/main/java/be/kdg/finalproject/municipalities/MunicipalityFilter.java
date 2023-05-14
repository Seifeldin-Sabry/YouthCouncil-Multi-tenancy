package be.kdg.finalproject.municipalities;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.exceptions.NoPlatformException;
import be.kdg.finalproject.repository.membership.MembershipRespository;
import be.kdg.finalproject.repository.municipality.MunicipalityRepository;
import be.kdg.finalproject.service.user.UserService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class MunicipalityFilter extends OncePerRequestFilter {
	private static final Logger LOGGER = Logger.getLogger(MunicipalityFilter.class.getName());

	private final MunicipalityRepository municipalityRepository;
	private final MembershipRespository membershipRepository;
	private final UserService userService;

	public MunicipalityFilter(MunicipalityRepository municipalityRepository, MembershipRespository membershipRepository, UserService userService) {
		this.municipalityRepository = municipalityRepository;
		this.membershipRepository = membershipRepository;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	                                FilterChain chain) throws ServletException, IOException {

		var municipality = getMunicipality(request);
		Municipality municipalityFound = municipalityRepository.findByNameIgnoreCase(municipality).orElse(null);
		if (municipalityFound == null) {
			// Attempted access to non-existing tenant
			MunicipalityContext.clear();
			logger.debug("current municipality is " + MunicipalityContext.getCurrentMunicipality());
			logger.debug("current municipalityName is " + MunicipalityContext.getCurrentMunicipalityName());
			chain.doFilter(request, response);
			return;
		}
		if (!municipalityFound.isHasPlatform()) {
			MunicipalityContext.clear();
			throw new NoPlatformException("Municipality does not have a platform yet");
		}
		MunicipalityContext.setCurrentMunicipalityName(municipality);
		MunicipalityContext.setCurrentMunicipality(municipalityFound);
		MunicipalityContext.setCurrentMunicipalityId(municipalityFound.getId());
		chain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getRequestURI().startsWith("/webjars/")
				|| request.getRequestURI().startsWith("/css/")
				|| request.getRequestURI().startsWith("/js/")
				|| request.getRequestURI().endsWith(".ico");
	}

	private String getMunicipality(HttpServletRequest request) {
		var domain = request.getServerName();
		var dotIndex = domain.indexOf(".");
		if (dotIndex != -1) {
			return domain.substring(0, dotIndex);
		}
		return "";
	}
}

