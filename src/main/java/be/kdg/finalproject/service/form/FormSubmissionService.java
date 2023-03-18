package be.kdg.finalproject.service.form;

import be.kdg.finalproject.domain.form.FormSubmission;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.form.FormSubmissionRepository;
import org.springframework.stereotype.Service;

@Service
public class FormSubmissionService {
    FormSubmissionRepository formSubmissionRepository;

    public FormSubmissionService(FormSubmissionRepository formSubmissionRepository) {
        this.formSubmissionRepository = formSubmissionRepository;
    }

    public FormSubmission addSubmission(FormSubmission formSubmission){
        return formSubmissionRepository.save(formSubmission);
    }

    public FormSubmission findSubmissionById(long id){
        return formSubmissionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("FormSubmission not found"));

    }
}
