package be.kdg.finalproject.controller.api.dto.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionPointDTO {
	private Long id;
	private String title;
	private UUID uuid;
	private LocalDate dateCreated;
	private String description;
	private Set<ActionPointProposalDTO> actionPointProposals;
	private Set<String> images;
	private int likeCount;
	private int followCount;
	private SubThemeDTO subTheme;
}
