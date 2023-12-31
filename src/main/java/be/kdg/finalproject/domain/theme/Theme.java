package be.kdg.finalproject.domain.theme;

import be.kdg.finalproject.domain.idea.CallForIdeas;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
	@Column (name = "is_active", nullable = false)
	private boolean isActive;
	@OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn (name = "theme_id")
	private List<SubTheme> subThemes = new ArrayList<>();

	@OneToMany(mappedBy = "theme")
	private List<CallForIdeas> callForIdeas = new ArrayList<>();

	public Theme(String themeName) {
		this.themeName = themeName;
		this.isActive = true;
	}

	public void addSubTheme(SubTheme subTheme) {
		subThemes.add(subTheme);
		subTheme.setTheme(this);
	}
}
