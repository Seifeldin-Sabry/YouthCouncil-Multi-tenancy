package be.kdg.finalproject.municipalities;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class MunicipalityAuthorizationFilter extends OncePerRequestFilter {
	private static final Logger LOGGER =
			Logger.getLogger(MunicipalityAuthorizationFilter.class.getName());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	                                FilterChain chain) throws ServletException, IOException {
		chain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getRequestURI().startsWith("/webjars/")
				|| request.getRequestURI().startsWith("/css/")
				|| request.getRequestURI().startsWith("/js/")
				|| request.getRequestURI().endsWith(".ico");
	}
}

