package be.kdg.finalproject.service.form;

import be.kdg.finalproject.domain.form.Form;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.form.FormRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

	private final FormRepository formRepository;

	@Autowired
	public FormService(FormRepository formRepository) {
		this.formRepository = formRepository;
	}


	public Form addForm(Form form) {
		return formRepository.save(form);
	}


	public Form updateForm(Form form) {
		return null;
	}


	public Form getFormById(Long id) {
		return formRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Form not found"));
	}

	public boolean formExists(long id){
		return formRepository.existsById(id);
	}


	public List<Form> getAllForms() {
		return ImmutableList.copyOf(formRepository.findAll());
	}
}
