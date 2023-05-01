package be.kdg.finalproject.domain.page.elements;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue ("IMAGE")
public class ImageElement extends PageElement {
	private String src;

	public ImageElement() {
		super();
	}

	public ImageElement(String src) {
		this.src = src;
	}
}
