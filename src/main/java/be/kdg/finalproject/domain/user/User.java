package be.kdg.finalproject.domain.user;

import be.kdg.finalproject.util.BcryptPasswordUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "user_id", nullable = false)
	private Long user_id;

	@Column (name = "first_name", nullable = false)
	private String first_name;

	@Column (name = "last_name", nullable = false)
	private String last_name;

	@Column (name = "email", nullable = false, length = 50)
	private String email;

	@Column (name = "password", nullable = false, length = 60)
	private String password;

	public User(String first_name, String last_name, String email, String password) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = BcryptPasswordUtil.hashPassword(password);
	}
}

