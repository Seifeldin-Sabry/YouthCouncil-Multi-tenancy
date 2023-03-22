package be.kdg.finalproject.service.membership;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.MembershipRespository;
import be.kdg.finalproject.repository.MunicipalityRepository;
import be.kdg.finalproject.repository.UserRepository;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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
		logger.debug("ALL MUUNICIPALITIES {}", ImmutableList.copyOf(municipalityRepository.findAll()));
		Municipality municipality = municipalityRepository.findByPostcode(postCode);
		logger.debug("Municipality {}", municipality);
		userRepository.save(user);
		Membership save = membershipRespository.save(new Membership(user, municipality, role));
		//		municipality.getMemberships().add(save);
		logger.debug("Municipality {}", save);
		return save;
	}

	@Override
	public List<Membership> getAllMembershipsByUser(User user) {
		return ImmutableList.copyOf(membershipRespository.findAllMembershipsByUser(user));
	}
}
