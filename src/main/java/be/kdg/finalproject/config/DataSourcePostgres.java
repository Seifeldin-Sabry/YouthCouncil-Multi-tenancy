package be.kdg.finalproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile({"prod"})
public class DataSourcePostgres implements DataSourceConfig{

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;


	@Bean
	@Override
	public DataSource setup() {
		return DataSourceBuilder.create()
		                        .driverClassName("org.postgresql.Driver")
		                        .url("jdbc:postgresql://localhost:5432/YouthCouncil")
		                        .username(username)
		                        .password(password)
		                        .build();
	}
}
