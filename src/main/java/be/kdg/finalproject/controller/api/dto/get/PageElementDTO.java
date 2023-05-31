package be.kdg.finalproject.controller.api.dto.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageElementDTO {
	private Long id;
	private String elementType;
	private String text;
	private int fontSize=12;
	private List<String> list;
	private String src;
	private int order;
}
