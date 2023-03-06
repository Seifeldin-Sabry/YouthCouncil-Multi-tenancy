package be.kdg.finalproject.domain.module;

import be.kdg.finalproject.domain.url.UrlItem;
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
@DiscriminatorValue ("list_reference_urls")
public class ListReferenceURLs extends Module {
	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UrlItem> referenceURLs = new HashSet<>();

	public ListReferenceURLs() {
		super();
		this.setModuleType(ModuleType.LIST_REFERENCE_URLS);
	}
}
