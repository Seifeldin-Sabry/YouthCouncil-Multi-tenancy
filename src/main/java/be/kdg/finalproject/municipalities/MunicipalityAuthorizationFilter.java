package be.kdg.finalproject.municipalities;

import be.kdg.finalproject.config.security.CustomOAuth2User;
import be.kdg.finalproject.config.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.logging.Logger;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class MunicipalityAuthorizationFilter extends OncePerRequestFilter {
	private static final Logger LOGGER =
			Logger.getLogger(MunicipalityAuthorizationFilter.class.getName());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	                                FilterChain chain) throws ServletException, IOException {
		if (MunicipalityContext.getCurrentMunicipality() != null && !MunicipalityContext.getCurrentMunicipality()
		                                                                                .isHasPlatform()) {
			response.sendRedirect("/error/no-platform");
			return;
		}
		var tenantId = MunicipalityContext.getCurrentMunicipalityId();
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			chain.doFilter(request, response);
			return;
		}
		Type type = authentication.getPrincipal() instanceof CustomOAuth2User ? CustomOAuth2User.class : CustomUserDetails.class;
		var user = type == CustomOAuth2User.class ? ((CustomOAuth2User) authentication.getPrincipal()) : ((CustomUserDetails) authentication.getPrincipal());
		Long userTenantId = user == null ? null : type == CustomOAuth2User.class ? ((CustomOAuth2User) authentication.getPrincipal()).getMunicipalityId() : ((CustomUserDetails) authentication.getPrincipal()).getMunicipalityId();
		if (Objects.equals(tenantId, userTenantId)) {
			chain.doFilter(request, response);
		} else {
			LOGGER.warning("Attempted cross-tenant access.");
			response.setStatus(FORBIDDEN.value());
		}

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getRequestURI().startsWith("/webjars/")
				|| request.getRequestURI().startsWith("/css/")
				|| request.getRequestURI().startsWith("/js/")
				|| request.getRequestURI().endsWith(".ico");
	}
}

