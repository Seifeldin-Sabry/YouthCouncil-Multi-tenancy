package be.kdg.finalproject.domain.actionpoint;

import be.kdg.finalproject.domain.location.Municipality;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ideas")
@Getter
@Setter
public class Idea {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "idea_id", nullable = false)
	private Long idea_id;
	@Column (name = "idea_description", nullable = false)
	private String ideaDescription;

	@Column (name = "image_url")
	private String imageUrl;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "theme_id", nullable = false)
	private Theme theme;
}
