package be.kdg.finalproject.domain.module;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue ("list_call_for_ideas")
public class ListCallForIdeas extends Module {
	public ListCallForIdeas() {
		super();
		this.setModuleType(ModuleType.LIST_CALL_FOR_IDEAS);
	}
}
