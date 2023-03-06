package be.kdg.finalproject.domain.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "MODULES")
@Getter
@Setter
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public abstract class Module {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "module_id", nullable = false)
	private Long id;

	@Column (name = "is_enabled")
	private boolean isEnabled;

	@Column (name = "module_type")
	private ModuleType moduleType;

	public Module() {
		this.isEnabled = false;
	}
}
