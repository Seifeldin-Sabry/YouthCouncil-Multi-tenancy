package be.kdg.finalproject.service.page;

import be.kdg.finalproject.controller.api.dto.patch.ActivateDTO;
import be.kdg.finalproject.controller.api.dto.post.NewInformativePageDTO;
import be.kdg.finalproject.domain.page.InformativePage;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.page.InformativePageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformativePageService {

	private final InformativePageRepository informativePageRepository;

	public InformativePageService(InformativePageRepository informativePageRepository) {
		this.informativePageRepository = informativePageRepository;
	}

	public List<InformativePage> getAllPages(Long municipalityId) {
		return informativePageRepository.findByMunicipalityId(municipalityId);
	}

	public List<InformativePage> getAllActivePages(Long municipalityId) {
		return informativePageRepository.findActiveByMunicipalityId(municipalityId);
	}

	public InformativePage getPageByName(Long muid, String pageName) {
		return informativePageRepository.findByMunicipalityIdAndPageName(muid, pageName)
		                                .orElseThrow(() -> new EntityNotFoundException("Page not found"));
	}

	public InformativePage getActivePageByName(Long muid, String pageName) {
		return informativePageRepository.findActivePageByMunicipalityIdAndPageName(muid, pageName)
		                                .orElseThrow(() -> new EntityNotFoundException("Page not found"));
	}

	public InformativePage addPage(NewInformativePageDTO newPage, Long muid) {
		InformativePage page = new InformativePage(newPage.getTitle());
		page.setMunicipalityId(muid);
		return informativePageRepository.save(page);
	}

	public void deletePage(Long id) {
		informativePageRepository.deleteById(id);
	}

	public InformativePage updatePage(Long id, InformativePage page) {
		return null;
	}

	public InformativePage updatePageActive(Long id, ActivateDTO page) {
		InformativePage informativePage = informativePageRepository.findById(id)
		                                                           .orElseThrow(() -> new EntityNotFoundException("Page not found"));
		informativePage.setActive(page.isActive());
		return informativePageRepository.save(informativePage);
	}
}
