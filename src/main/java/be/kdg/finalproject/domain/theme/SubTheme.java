package be.kdg.finalproject.domain.theme;

import be.kdg.finalproject.domain.idea.Idea;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;

@Entity (name = "SUB_THEMES")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubTheme {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "sub_theme_id", nullable = false)
	private Long id;

	@Unique
	@Column (name = "sub_them_name", nullable = false)
	private String subThemeName;

	private boolean isActive;

	@ManyToOne
	@JoinColumn (name = "theme_id")
	@ToString.Exclude
	private Theme theme;

	@ManyToOne
	private Idea idea;

	public SubTheme(String subThemeName) {
		this.subThemeName = subThemeName;
		this.isActive = true;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
