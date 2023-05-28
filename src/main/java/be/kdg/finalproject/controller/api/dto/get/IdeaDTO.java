package be.kdg.finalproject.controller.api.dto.get;

import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IdeaDTO {
	private String title;
	private String content;
	private SubThemeDTO subTheme;
	private Long id;
	private Timestamp dateCreated;
	private UserDTO creator;
	private boolean liked;
	private Set<UserIdeaLikeDTO> likers;
	private String image;
}
