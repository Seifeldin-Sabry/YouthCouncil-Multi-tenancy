package be.kdg.finalproject.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordUtil {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public BCryptPasswordUtil() {
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

	public String encode(String password) {
		return bCryptPasswordEncoder.encode(password);
	}
}
