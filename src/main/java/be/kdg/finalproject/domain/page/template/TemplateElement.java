package be.kdg.finalproject.domain.page.template;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

public enum TemplateElement {
	LIST("list-placeholder.png"), TEXT("text-placeholder.jpeg"), IMAGE("image-placeholder.jpeg");

	@Getter
	private String name;

	@Value ("${images.placeholderDir}")
	private String placeholderDir = "/images/placeholders/";

	TemplateElement(String name) {
		this.name = placeholderDir + name;
	}
}
