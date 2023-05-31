package be.kdg.finalproject.domain.page.elements;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue ("LIST")
public class ListElement extends PageElement {

	@ElementCollection (fetch = FetchType.EAGER)
	private List<String> list = new ArrayList<>();

	public ListElement() {
		super();
		setElementType("LIST");
	}

	public ListElement(List<String> list) {
		this.list = list;
		setElementType("LIST");
	}

	public void addListItem(String listItem) {
		list.add(listItem);
		setElementType("LIST");
	}
}
