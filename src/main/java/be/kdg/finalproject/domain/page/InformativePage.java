package be.kdg.finalproject.domain.page;

import be.kdg.finalproject.domain.platform.Municipality;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity (name = "PAGES")
@Getter
@Setter
@NoArgsConstructor
public class InformativePage {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "page_id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn (name = "municipality_id", nullable = false)
	private Municipality municipality;

	private String title;
	private String description;

	public InformativePage(String title, String description) {
		this.title = title;
		this.description = description;
	}
}
