package be.kdg.finalproject.domain.actionpoint;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "action_points")
@Getter
@Setter
public class ActionPoint {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "idea_id", nullable = false)
	private Long idea_id;
}
