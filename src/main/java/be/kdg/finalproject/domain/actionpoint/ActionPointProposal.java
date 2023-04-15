package be.kdg.finalproject.domain.actionpoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity (name = "ACTION_POINT_PROPOSALS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class ActionPointProposal {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "action_point_proposal_id", nullable = false)
	private Long id;

	@Column (name = "status", nullable = false)
	@Enumerated (EnumType.STRING)
	private ActionPointProposalStatus status;

	@Column (name = "proposal", nullable = false, columnDefinition = "TEXT")
	private String proposal;

	public ActionPointProposal(String description) {
		this(ActionPointProposalStatus.NOT_INCLUDED, description);
	}

	public ActionPointProposal(ActionPointProposalStatus status, String proposal) {
		this.status = status;
		this.proposal = proposal;
	}
}
