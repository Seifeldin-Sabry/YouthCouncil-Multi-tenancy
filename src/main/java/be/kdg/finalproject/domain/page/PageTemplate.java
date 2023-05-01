package be.kdg.finalproject.domain.page;

import be.kdg.finalproject.domain.page.template.TemplateElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity (name = "PAGE_TEMPLATES")
@Getter
@Setter
@Table
@NoArgsConstructor
@ToString
public class PageTemplate {
	@Id
	@Column (name = "template_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	private UUID uuid = UUID.randomUUID();

	@Column (name = "template_name")
	private String name;

	@ElementCollection (fetch = FetchType.EAGER)
	@Enumerated (EnumType.STRING)
	private List<TemplateElement> elements;

	public PageTemplate(String name) {
		this.name = name;
	}

	public PageTemplate(String name, List<TemplateElement> elements) {
		this.name = name;
		this.elements = elements;
	}

	public void addElement(TemplateElement element) {
		this.elements.add(element);
	}
}
