package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.idea.Idea;
import org.springframework.data.repository.CrudRepository;

public interface IdeasRepository extends CrudRepository<Idea, Long> {
}
