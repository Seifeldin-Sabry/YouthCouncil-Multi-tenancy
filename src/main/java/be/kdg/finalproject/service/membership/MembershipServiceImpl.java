package be.kdg.finalproject.service.membership;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.MembershipRespository;
import be.kdg.finalproject.repository.MunicipalityRepository;
import be.kdg.finalproject.repository.UserRepository;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MembershipServiceImpl implements MembershipService {

	private final MembershipRespository membershipRespository;
	private final MunicipalityRepository municipalityRepository;
	private final Logger logger = LoggerFactory.getLogger(MembershipServiceImpl.class);
	private final UserRepository userRepository;

	public MembershipServiceImpl(MembershipRespository membershipRespository, MunicipalityRepository municipalityRepository,
	                             UserRepository userRepository) {
		this.membershipRespository = membershipRespository;
		this.municipalityRepository = municipalityRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Membership addMembershipByUserAndPostCode(User user, Integer postCode, Role role) {
		Municipality municipality = municipalityRepository.findByPostcode(postCode);
		logger.debug("Municipality {}", municipality);
		Membership save = membershipRespository.save(new Membership(user, municipality, role));
		if (role == Role.YOUTH_COUNCIL_ADMINISTRATOR) {
			municipality.setHasPlatform(true);
			municipality.addMember(user);
		} else if (role == Role.YOUTH_COUNCIL_MODERATOR) {
			municipality.addMember(user);
		}
		municipalityRepository.save(municipality);
		logger.debug("Municipality {}", save);
		return save;
	}

	@Override
	public List<Membership> getAllMembershipsByUser(User user) {
		return ImmutableList.copyOf(membershipRespository.findAllMembershipsByUser(user));
	}

	@Override
	public void addMembershipByUserAndUuid(User newUser, UUID uuid, Role user) {
		Municipality municipality = municipalityRepository.findByUuid(uuid)
		                                                  .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
		membershipRespository.save(new Membership(newUser, municipality, user));
		if (user == Role.YOUTH_COUNCIL_ADMINISTRATOR) {
			municipality.setHasPlatform(true);
		}
		municipality.addMember(newUser);
		municipalityRepository.save(municipality);
	}
}
