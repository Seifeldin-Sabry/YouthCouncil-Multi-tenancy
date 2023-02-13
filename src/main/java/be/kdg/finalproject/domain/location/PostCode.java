package be.kdg.finalproject.domain.location;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "postcodes")
@Getter
@Setter
public class PostCode {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "postcode_id", nullable = false)
	private Short postcode;
	@Column (name = "municipality_id", nullable = false, insertable = false, updatable = false)
	private Long municipality_id;
}
