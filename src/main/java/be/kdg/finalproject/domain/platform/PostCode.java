package be.kdg.finalproject.domain.platform;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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


	public PostCode(Integer postcode) {
		this.postcode = postcode;
	}
}
