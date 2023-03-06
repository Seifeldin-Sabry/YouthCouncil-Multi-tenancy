package be.kdg.finalproject.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile ("dev")
public class HSQLDatabaseSeeder implements DatabaseSeeder {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public HSQLDatabaseSeeder(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void seedDatabase() {
		//		jdbcTemplate.execute("""
		//			INSERT INTO APP_USERS (firtname, lastname, email, password, role) VALUES
		//			('seif', 'sabry', )
		//			""");
	}


}
