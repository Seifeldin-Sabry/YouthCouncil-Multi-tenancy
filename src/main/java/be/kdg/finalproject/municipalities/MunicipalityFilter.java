package be.kdg.finalproject.municipalities;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.repository.MunicipalityRepository;
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

	public MunicipalityFilter(MunicipalityRepository municipalityRepository) {
		this.municipalityRepository = municipalityRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	                                FilterChain chain) throws ServletException, IOException {
		var municipality = getMunicipality(request);
		if (municipality.isEmpty()) {
			// This is a request to the root URL path, do not set municipality context
			MunicipalityContext.clear();
			chain.doFilter(request, response);
			return;
		}
		Municipality municipalityId = municipalityRepository.findByNameIgnoreCase(municipality).orElse(null);
		if (municipalityId == null || !municipalityId.isHasPlatform()) {
			// Attempted access to non-existing tenant
			MunicipalityContext.clear();
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		LOGGER.info("Setting tenant: " + municipality + " (domain " + request.getServerName() + ")");
		LOGGER.info("Setting tenant ID: " + municipalityId.getId());
		MunicipalityContext.setCurrentMunicipality(municipality);
		MunicipalityContext.setCurrentMunicipalityId(municipalityId.getId());
		LOGGER.info("Current municipality: " + municipality);
		LOGGER.info("Current municipality ID: " + municipalityId.getId());
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

