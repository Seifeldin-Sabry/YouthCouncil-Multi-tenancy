package be.kdg.finalproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.NOT_FOUND)
public class NoPlatformException extends RuntimeException {
	public NoPlatformException(String s) {
		super(s);
	}
}
