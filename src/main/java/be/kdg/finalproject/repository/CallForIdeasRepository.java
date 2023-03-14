package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.idea.CallForIdea;
import org.springframework.data.repository.CrudRepository;

public interface CallForIdeasRepository extends CrudRepository<CallForIdea, Long> {
}
