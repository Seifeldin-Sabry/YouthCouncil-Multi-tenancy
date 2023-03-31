package be.kdg.finalproject.municipalities;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class MunicipalityResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return (parameter.getParameterAnnotation(Municipality.class) != null &&
				parameter.getParameterType() == String.class) ||
				(parameter.getParameterAnnotation(MunicipalityId.class) != null &&
						parameter.getParameterType() == Long.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
	                              NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		if (parameter.getParameterAnnotation(Municipality.class) != null) {
			return MunicipalityContext.getCurrentMunicipality();
		}
		return MunicipalityContext.getCurrentMunicipalityId();
	}
}


