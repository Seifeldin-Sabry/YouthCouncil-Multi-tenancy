package be.kdg.finalproject.service.page;

import be.kdg.finalproject.controller.api.dto.post.NewPageElementDTO;
import be.kdg.finalproject.domain.page.InformativePage;
import be.kdg.finalproject.domain.page.elements.ImageElement;
import be.kdg.finalproject.domain.page.elements.ListElement;
import be.kdg.finalproject.domain.page.elements.PageElement;
import be.kdg.finalproject.domain.page.elements.TextElement;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.page.ImageElementRepository;
import be.kdg.finalproject.repository.page.ListElementRepository;
import be.kdg.finalproject.repository.page.TextElementRepository;
import be.kdg.finalproject.service.media.ImageService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PageElementService {
	private final ImageElementRepository imageElementRepository;
	private final ListElementRepository listElementRepository;
	private final TextElementRepository textElementRepository;
	private final ImageService imageService;

	public PageElementService(ImageElementRepository imageElementRepository, ListElementRepository listElementRepository,
	                          TextElementRepository textElementRepository, ImageService imageService) {
		this.imageElementRepository = imageElementRepository;
		this.listElementRepository = listElementRepository;
		this.textElementRepository = textElementRepository;
		this.imageService = imageService;
	}
	public TextElement addTextElement(NewPageElementDTO newPageElementDTO, InformativePage page){
		TextElement element = new TextElement();
		element.setText(newPageElementDTO.getText());
		element.setFontSize(newPageElementDTO.getFontSize());
		element.setPage(page);
		element.setPageOrder(newPageElementDTO.getOrder());
		return textElementRepository.save(element);
	}
	public ImageElement addImageElement(NewPageElementDTO newPageElementDTO, InformativePage page) throws IOException {
		ImageElement element = new ImageElement();
		List<String> imageSources = imageService.saveImages(newPageElementDTO.getSrc());
		element.setSrc(imageSources.get(0));
		element.setPage(page);
		element.setPageOrder(newPageElementDTO.getOrder());
		return imageElementRepository.save(element);
	}
	public ListElement addListElement(NewPageElementDTO newPageElementDTO, InformativePage page){
		ListElement element = new ListElement();
		element.setList(new ArrayList<>(){{add(newPageElementDTO.getText());}});
		element.setPage(page);
		element.setPageOrder(newPageElementDTO.getOrder());
		return listElementRepository.save(element);
	}

	public ListElement updateListElementList(String listItem, Long listId){
		ListElement listElement = listElementRepository.findById(listId).orElseThrow(() ->
				new EntityNotFoundException("List Element not found"));
		listElement.addListItem(listItem);
		return listElementRepository.save(listElement);
	}

	public void deleteLastListItem(Long listId){
		ListElement listElement = listElementRepository.findById(listId).orElseThrow(() ->
				new EntityNotFoundException("List Element not found"));
		listElement.getList().remove(listElement.getList().size()-1);
		listElementRepository.save(listElement);
	}

	@Transactional
	public void updateOrder(int order, String type,  Long id){
		switch (type) {
			case "heading", "paragraph", "TEXT" -> {
				textElementRepository.updateOrderById(order, id);
			}
			case "image", "IMAGE" -> {
				imageElementRepository.updateOrderById(order, id);
			}
			case "list", "LIST" -> {
				listElementRepository.updateOrderById(order, id);
			}
			default -> throw new EntityNotFoundException("Element Type not found");
		}
	}
}

