package be.kdg.finalproject.domain.interaction.like;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Emoji {
	LIKE(0x1F44D),
	LOVE(0x1F60D),
	HAHA(0x1F602),
	WOW(0x1F62E),
	SAD(0x1F622),
	ANGRY(0x1F620);

	private final int codePoint;
}