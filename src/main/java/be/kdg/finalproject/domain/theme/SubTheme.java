package be.kdg.finalproject.domain.theme;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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

	@ManyToOne
	@JoinColumn (name = "theme_id")
	@ToString.Exclude
	private Theme theme;

	public SubTheme(String subThemeName) {
		this.subThemeName = subThemeName;
	}
}
