package be.kdg.finalproject.domain.actionpoint;

import lombok.Getter;

@Getter
public enum ActionPointProposalStatus {
	NOT_INCLUDED("bg-danger", "Not included"),
	INCLUDED("bg-info", "Included"),
	IN_PROGRESS("bg-warning", "In progress"),
	REALIZED("bg-success", "Realized");

	private String bootstrapClass;
	private String prettyName;

	ActionPointProposalStatus(String bootstrapClass, String prettyName) {
		this.bootstrapClass = bootstrapClass;
		this.prettyName = prettyName;
	}
}
