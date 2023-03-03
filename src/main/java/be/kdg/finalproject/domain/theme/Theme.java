package be.kdg.finalproject.domain.theme;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "themes")
@Getter
@Setter
public class Theme {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "theme_id", nullable = false)
	private Long id;

	@Column (name = "theme_name", nullable = false)
	private String themeName;
}
