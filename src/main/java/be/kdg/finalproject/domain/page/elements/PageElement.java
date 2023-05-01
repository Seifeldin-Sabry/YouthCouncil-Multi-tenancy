package be.kdg.finalproject.domain.page.elements;

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
}
