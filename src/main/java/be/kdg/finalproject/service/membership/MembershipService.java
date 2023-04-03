package be.kdg.finalproject.service.membership;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.membership.MembershipRespository;
import be.kdg.finalproject.repository.membership.UserRepository;
import be.kdg.finalproject.repository.municipality.MunicipalityRepository;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MembershipService {

	private final MembershipRespository membershipRepository;
	private final MunicipalityRepository municipalityRepository;
	private final Logger logger = LoggerFactory.getLogger(MembershipService.class);
	private final UserRepository userRepository;

	public MembershipService(MembershipRespository membershipRepository, MunicipalityRepository municipalityRepository,
	                         UserRepository userRepository) {
		this.membershipRepository = membershipRepository;
		this.municipalityRepository = municipalityRepository;
		this.userRepository = userRepository;
	}


	public Membership addMembershipByUserAndMunicipalityId(User user, Long municipalityId, Role role) {
		Municipality municipality = municipalityRepository.findMunicipalityThatHasPlatformWithMembers(municipalityId)
		                                                  .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
		logger.debug("Municipality {}", municipality);
		Membership save = membershipRepository.save(new Membership(user, municipality, role));
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


	public List<Membership> getAllMembershipsByUser(User user) {
		return ImmutableList.copyOf(membershipRepository.findAllMembershipsByUser(user));
	}


	public void addMembershipByUserAndUuid(User newUser, UUID uuid, Role user) {
		Municipality municipality = municipalityRepository.findByUuid(uuid)
		                                                  .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
		membershipRepository.save(new Membership(newUser, municipality, user));
		if (user == Role.YOUTH_COUNCIL_ADMINISTRATOR) {
			municipality.setHasPlatform(true);
		}
		municipality.addMember(newUser);
		municipalityRepository.save(municipality);
	}


	public Membership getMembershipByUserAndMunicipalityName(User user, String municipalityName) {
		logger.debug("User {} and municipality {}", user, municipalityName);
		return membershipRepository.findByUser_Memberships_UserAndMunicipality_NameIgnoreCase(user, municipalityName)
		                           .orElse(null);
	}


	public List<Membership> getMembershipsByMunicipalityIdWhereNotAdmin(Long municipalityId) {
		logger.debug("Municipality id {}", municipalityId);
		return membershipRepository.findByMunicipalityIdWithUsers(municipalityId);
	}

	public boolean memberExistByUserEmailAndMunicipalityId(String email, Long municipalityId) {
		return membershipRepository.existsByUserEmailAndMunicipalityId(email, municipalityId);
	}

	public void addMembershipByUserEmailAndMunicipalityId(String email, Long municipalityId) {
		Municipality municipality = municipalityRepository.findMunicipalityThatHasPlatformWithMembers(municipalityId)
		                                                  .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
		User user = userRepository.findByUsernameOrEmail(email)
		                          .orElseThrow(() -> new EntityNotFoundException("User not found"));
		membershipRepository.save(new Membership(user, municipality, Role.USER));
	}

	public void updateBanStatus(Long membershipId, boolean ban) {
		Membership membership = membershipRepository.findById(membershipId)
		                                            .orElseThrow(() -> new EntityNotFoundException("Membership not found"));
		membership.setBanned(ban);
		membershipRepository.save(membership);
	}

	public void updatePromoteStatus(Long membershipId, boolean promote) {
		Membership membership = membershipRepository.findById(membershipId)
		                                            .orElseThrow(() -> new EntityNotFoundException("Membership not found"));
		if (promote) {
			membership.setRole(Role.YOUTH_COUNCIL_MODERATOR);
		} else {
			membership.setRole(Role.USER);
		}
		membershipRepository.save(membership);
	}
}
