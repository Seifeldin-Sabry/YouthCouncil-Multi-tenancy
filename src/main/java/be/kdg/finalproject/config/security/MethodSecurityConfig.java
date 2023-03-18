package be.kdg.finalproject.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity (
		securedEnabled = true,
		jsr250Enabled = true)
public class MethodSecurityConfig {
	@Bean
	public MethodSecurityExpressionHandler createExpressionHandler() {
		return new DefaultMethodSecurityExpressionHandler();
	}
}

