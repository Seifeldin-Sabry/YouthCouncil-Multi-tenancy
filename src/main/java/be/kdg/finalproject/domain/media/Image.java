package be.kdg.finalproject.domain.media;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "images")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Image {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "image_id", nullable = false)
	private Long id;

	@Column (name = "file_path", nullable = false)
	private String filePath;

	@Column (name = "file_size_bytes", nullable = false)
	private Long sizeInBytes;
}
