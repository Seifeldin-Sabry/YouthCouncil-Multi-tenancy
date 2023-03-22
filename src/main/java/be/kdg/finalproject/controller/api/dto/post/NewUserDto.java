package be.kdg.finalproject.controller.api.dto.post;


import javax.validation.constraints.NotBlank;

public class NewUserDto {

	@NotBlank
	private String first_name;
	@NotBlank
	private String surname;
	@NotBlank
	private String username;
	@NotBlank
	private String postcode;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	private Role role;
	private long id;

	public NewUserDto(String first_name, String surname, String username, String postcode, String email, String password) {
		this.first_name = first_name;
		this.surname = surname;
		this.username = username;
		this.postcode = postcode;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
