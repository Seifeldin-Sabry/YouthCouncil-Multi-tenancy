package be.kdg.finalproject.controller.api.dto.get;

import be.kdg.finalproject.domain.interaction.like.UserIdeaLike;
import be.kdg.finalproject.domain.theme.Theme;
import be.kdg.finalproject.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdeaDTO {
	private String content;
	private SubThemeDTO subTheme;
	private UUID uuid;
	private Long id;
	private Timestamp dateCreated;
	private UserDTO creator;
	private boolean liked;
	private Set<UserIdeaLikeDTO> likers;
	private String image;
}
