package be.kdg.finalproject.domain.theme;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "THEMES")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Theme {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "theme_id", nullable = false)
	private Long id;

	@Column (name = "theme_name", nullable = false)
	private String themeName;
	private boolean isActive;
	@OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn (name = "theme_id")
	private Set<SubTheme> subThemes = new HashSet<>();

	public Theme(String themeName) {
		this.themeName = themeName;
	}

	public void addSubTheme(SubTheme subTheme) {
		subThemes.add(subTheme);
		subTheme.setTheme(this);
	}
}
