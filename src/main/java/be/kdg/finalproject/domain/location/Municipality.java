package be.kdg.finalproject.domain.location;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "municipalities")
@Getter
@Setter
public class Municipality {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "municipalitiy_id", nullable = false)
	private Long municipality_id;
	@Column (name = "name", nullable = false)
	private String name;
}
