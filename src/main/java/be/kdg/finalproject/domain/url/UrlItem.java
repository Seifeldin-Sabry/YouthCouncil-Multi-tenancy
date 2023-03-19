package be.kdg.finalproject.domain.url;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity (name = "URL_ITEMS")
@Getter
@Setter
@NoArgsConstructor
public class UrlItem {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (name = "url", nullable = false)
	private String url;
	@Column (name = "description", nullable = false)
	private String description;

	public UrlItem(String url, String description) {
		this.url = url;
		this.description = description;
	}
}
