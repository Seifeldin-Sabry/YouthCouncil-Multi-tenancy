package be.kdg.finalproject.domain.platform;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table (name = "social_media_links")
public class SocialMediaLink {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "social_media_link_id")
	private Long id;

	@Column (name = "name_social_media")
	private String nameSocialMedia;

	@Column (name = "link")
	private String link;

	public SocialMediaLink(String nameSocialMedia, String link) {
		this.nameSocialMedia = nameSocialMedia;
		this.link = link;
	}
}
