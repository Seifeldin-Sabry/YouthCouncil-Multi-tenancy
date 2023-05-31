package be.kdg.finalproject.domain.page.elements;

import be.kdg.finalproject.domain.page.InformativePage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "element_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public abstract class PageElement {
	@Id
	@Column (name = "element_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column (name = "element_type", insertable = false, updatable = false)
	private String elementType;
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "page_name", nullable = false)
	private InformativePage page;

	@Column(name = "page_order", nullable = false)
	private int pageOrder;
}
