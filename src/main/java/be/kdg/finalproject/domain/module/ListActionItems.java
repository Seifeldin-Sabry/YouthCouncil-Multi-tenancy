package be.kdg.finalproject.domain.module;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue ("list_action_items")
public class ListActionItems extends Module {
	public ListActionItems() {
		super();
		this.setModuleType(ModuleType.LIST_ACTION_ITEMS);
	}
}
