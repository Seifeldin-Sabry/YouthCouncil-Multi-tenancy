package be.kdg.finalproject.domain.page;

import be.kdg.finalproject.domain.platform.Municipality;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

	@Column (name = "title", nullable = false)
	private String title;

	@ElementCollection (fetch = FetchType.EAGER)
	private List<String> content = new ArrayList<>();

	public InformativePage(String title, Municipality municipality) {
		this.title = title;
	}

	public InformativePage(String title) {
		this.title = title;
	}

	public void addContent(String content) {
		this.content.add(content);
	}

	public void removeContent(String content) {
		this.content.remove(content);
	}

}
