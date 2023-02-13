package be.kdg.finalproject.util;

import static org.springframework.security.crypto.bcrypt.BCrypt.*;
import static org.springframework.security.crypto.bcrypt.BCrypt.gensalt;

/**
 * Purpose of this class: to encrypt passwords, and to check if a password matches a given hash.
 */
public class BcryptPasswordUtil {
	public static String hashPassword(String password) {
		return hashpw(password, gensalt());
	}

	public static boolean checkPassword(String password, String hashedPassword) {
		return checkpw(password, hashedPassword);
	}
}

