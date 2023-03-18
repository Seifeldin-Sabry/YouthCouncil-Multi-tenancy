package be.kdg.finalproject.domain.news;

import be.kdg.finalproject.domain.platform.Municipality;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity (name = "NEWS_ITEMS")
@Getter
@Setter
@NoArgsConstructor
public class NewsItem {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "news_item_id", nullable = false)
	private Long id;
	@Column (name = "title", nullable = false)
	private String title;
	@Column (name = "content", nullable = false)
	private String content;
	@Column (name = "date")
	private LocalDate date;
	@ManyToOne
	@JoinColumn (name = "municipality_id", nullable = false)
	private Municipality municipality;

	public NewsItem(String title, String content) {
		this.title = title;
		this.content = content;
		this.date = LocalDate.now();
	}
}
