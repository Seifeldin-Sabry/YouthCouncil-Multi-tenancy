package be.kdg.finalproject.service.page;

import be.kdg.finalproject.domain.page.PageTemplate;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.page.PageTemplateRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class PageTemplateService {
	private final PageTemplateRepository pageTemplateRepository;

	public PageTemplateService(PageTemplateRepository pageTemplateRepository) {
		this.pageTemplateRepository = pageTemplateRepository;
	}

	public List<PageTemplate> getAllPageTemplates() {
		return ImmutableList.copyOf(pageTemplateRepository.findAll());
	}

	public PageTemplate addPageTemplate(PageTemplate pageTemplate) {
		return pageTemplateRepository.save(pageTemplate);
	}

	public PageTemplate getPageTemplateByUuid(UUID uuid) {
		return pageTemplateRepository.findByUuid(uuid)
		                             .orElseThrow(() -> new EntityNotFoundException("PageTemplate not found"));
	}

	public void deletePageTemplate(Long id) {
		PageTemplate pageTemplate = pageTemplateRepository.findById(id)
		                                                  .orElseThrow(() -> new EntityNotFoundException("PageTemplate not found"));
		pageTemplateRepository.delete(pageTemplate);
	}
}
