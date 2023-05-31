package be.kdg.finalproject.domain.page.elements;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue ("IMAGE")
public class ImageElement extends PageElement {
	@Column(name = "src")
	private String src;

	public ImageElement() {
		super();
		setElementType("IMAGE");
	}

	public ImageElement(String src) {
		this.src = src;
		setElementType("IMAGE");
	}
}
