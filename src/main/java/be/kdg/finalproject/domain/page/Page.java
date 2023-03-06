package be.kdg.finalproject.domain.page;

import be.kdg.finalproject.domain.module.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity (name = "PAGES")
@Getter
@Setter
@NoArgsConstructor
public class Page {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "page_id", nullable = false)
	private Long id;
	private String title;
	private String description;
	@OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
	private ListActionItems listActionItems = new ListActionItems();
	@OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
	private ListCalendarActivites listCalendarActivites = new ListCalendarActivites();
	@OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
	private ListCallForIdeas listCallForIdeas = new ListCallForIdeas();
	@OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
	private ListNewsItems listNewsItems = new ListNewsItems();
	@OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
	private ListReferenceURLs listReferenceURLs = new ListReferenceURLs();

	public Page(String title, String description) {
		this.title = title;
		this.description = description;
	}
}
