package be.kdg.finalproject.domain.location;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "postcodes")
@Getter
@Setter
@NoArgsConstructor
public class PostCode {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "postcode_id", nullable = false)
	private Long id;

	@Column (name = "postcode", nullable = false)
	private Integer postcode;

	@Column(name = "municipality_id", nullable = false)
	private Long municipalityId;
}
