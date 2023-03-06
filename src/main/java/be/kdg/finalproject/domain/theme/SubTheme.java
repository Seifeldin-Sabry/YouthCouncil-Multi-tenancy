package be.kdg.finalproject.domain.theme;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity (name = "SUB_THEMES")
@Getter
@Setter
@NoArgsConstructor
public class SubTheme {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "sub_theme_id", nullable = false)
	private Long id;

	@Unique
	@Column (name = "sub_them_name", nullable = false)
	private String subThemeName;
}
