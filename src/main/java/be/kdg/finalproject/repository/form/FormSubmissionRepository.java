package be.kdg.finalproject.repository.form;

import be.kdg.finalproject.domain.form.FormSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormSubmissionRepository extends JpaRepository<FormSubmission, Long> {
}
