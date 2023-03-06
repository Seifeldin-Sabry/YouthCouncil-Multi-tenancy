package be.kdg.finalproject.domain.module;

import be.kdg.finalproject.domain.news.NewsItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue ("list_news_items")
public class ListNewsItems extends Module {
	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<NewsItem> newsItems = new HashSet<>();

	public ListNewsItems() {
		super();
		this.setModuleType(ModuleType.LIST_NEWS_ITEMS);
	}
}
