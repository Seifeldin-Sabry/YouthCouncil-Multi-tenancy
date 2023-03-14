package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.user.Membership;
import org.springframework.data.repository.CrudRepository;

public interface MembershipRespository extends CrudRepository<Membership, Long> {
}
