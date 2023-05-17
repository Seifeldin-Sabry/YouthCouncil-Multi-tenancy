package be.kdg.finalproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalProjectApplication {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(FinalProjectApplication.class);
		String postgresPassword = System.getenv("POSTGRES_PROD_PASSWORD");
		String postgresUsername = System.getenv("POSTGRES_PROD_USERNAME");
		String googleClientId = System.getenv("GOOGLE_CLIENT_ID");
		String googleClientSecret = System.getenv("GOOGLE_CLIENT_SECRET");
		String facebookClientId = System.getenv("FACEBOOK_CLIENT_ID");
		String facebookClientSecret = System.getenv("FACEBOOK_CLIENT_SECRET");
		String projectID = System.getenv("GOOGLE_PROJECT_ID");
		String postgresDB = System.getenv("POSTGRES_DB");
		String sqlInstance = System.getenv("SQL_INSTANCE_CONNECTION_NAME");

		logger.error("Environment variables:");
		logger.error("POSTGRES_PROD_PASSWORD = " + postgresPassword);
		logger.error("POSTGRES_PROD_USERNAME = " + postgresUsername);
		logger.error("GOOGLE_CLIENT_ID = " + googleClientId);
		logger.error("GOOGLE_CLIENT_SECRET = " + googleClientSecret);
		logger.error("FACEBOOK_CLIENT_ID = " + facebookClientId);
		logger.error("FACEBOOK_CLIENT_SECRET = " + facebookClientSecret);
		logger.error("PROJECT_ID = " + projectID);
		logger.error("POSTGRES_DB = " + postgresDB);
		logger.error("SQL_INSTANCE_NAME = " + sqlInstance);
		SpringApplication.run(FinalProjectApplication.class, args);
	}

}
