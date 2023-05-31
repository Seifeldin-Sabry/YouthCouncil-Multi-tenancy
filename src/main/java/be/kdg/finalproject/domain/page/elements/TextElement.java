package be.kdg.finalproject.domain.page.elements;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@DiscriminatorValue ("TEXT")
public class TextElement extends PageElement {

	@Transient
	private final int DEFAULT_FONT_SIZE = 30;

	@Column (name = "text")
	private String text;

	@Column (name = "font_size")
	private int fontSize = DEFAULT_FONT_SIZE;

	public TextElement() {
		super();
		setElementType("TEXT");
	}

	public TextElement(String text) {
		this.text = text;
		setElementType("TEXT");
	}

	public TextElement(String text, int fontSize) {
		this.text = text;
		this.fontSize = fontSize;
		setElementType("TEXT");
	}
}
