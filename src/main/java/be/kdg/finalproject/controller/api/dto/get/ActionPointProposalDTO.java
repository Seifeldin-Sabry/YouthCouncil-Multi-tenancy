package be.kdg.finalproject.controller.api.dto.get;

import be.kdg.finalproject.domain.actionpoint.ActionPointProposalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionPointProposalDTO {
	private Long id;
	private String proposal;
	private ActionPointProposalStatus status;
}
