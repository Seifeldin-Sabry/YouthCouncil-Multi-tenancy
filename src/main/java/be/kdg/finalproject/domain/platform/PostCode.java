package be.kdg.finalproject.domain.platform;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity (name = "POSTCODES")
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
}
