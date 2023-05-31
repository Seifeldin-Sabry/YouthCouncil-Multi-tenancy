package be.kdg.finalproject.domain.page;

import be.kdg.finalproject.domain.page.elements.PageElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

	@Column (name = "title", nullable = false)
	private String title;

	@OneToMany (mappedBy = "page", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PageElement> elements;

	private boolean active = false;

	private String pageName;

	@JoinColumn (name = "municipalitiy_id", updatable = false, insertable = false)
	private Long municipalityId;

	public InformativePage(String title) {
		this.title = title;
		this.pageName = title.toLowerCase().replace(" ", "-");
	}

}
