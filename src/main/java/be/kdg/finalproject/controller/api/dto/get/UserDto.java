package be.kdg.finalproject.controller.api.dto.get;


import javax.validation.constraints.NotBlank;

public class UserDto {
	@NotBlank
	private String username;
	@NotBlank
	private String first_name;
	@NotBlank
	private String last_name;
	@NotBlank
	private String email;
	@NotBlank
	private String password;


	public UserDto(String username, String first_name, String last_name, String email, String password) {
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
