package be.kdg.finalproject.service.membership;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.MembershipRespository;
import be.kdg.finalproject.repository.MunicipalityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembershipServiceImpl implements MembershipService {

	private final MembershipRespository membershipRespository;
	private final MunicipalityRepository municipalityRepository;
	private final Logger logger = LoggerFactory.getLogger(MembershipServiceImpl.class);

	public MembershipServiceImpl(MembershipRespository membershipRespository, MunicipalityRepository municipalityRepository) {
		this.membershipRespository = membershipRespository;
		this.municipalityRepository = municipalityRepository;
	}

	@Override
	@Transactional
	public Membership addMembershipByUserAndPostCode(User user, Integer postCode) {
		Municipality municipality = municipalityRepository.findByPostcode(postCode);
		Membership membership = new Membership(user, municipality);
		municipalityRepository.save(municipality);
		Membership save = membershipRespository.save(membership);
		logger.debug("{}", save);
		logger.debug("municipality: {}", municipality.getMemberships());
		return save;
	}
}
