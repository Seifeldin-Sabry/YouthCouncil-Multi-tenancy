package be.kdg.finalproject.service.membership;

import be.kdg.finalproject.controller.api.dto.post.NewUserDto;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.security.Provider;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {

	private final MembershipRespository membershipRepository;
	private final MunicipalityRepository municipalityRepository;
	private final Logger logger = LoggerFactory.getLogger(MembershipService.class);
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public MembershipService(MembershipRespository membershipRepository, MunicipalityRepository municipalityRepository,
	                         UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.membershipRepository = membershipRepository;
		this.municipalityRepository = municipalityRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
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


	public User addNewMembershipByUserAndId(NewUserDto user, Long id, Role userRole) {
		//TODO: email password to user
		User newUser = new User(user.getFirstName(), user.getSurname(), user.getEmail(), user.getEmail(), passwordEncoder.encode("password"), user.getRole(), Provider.LOCAL);
		Municipality municipality = municipalityRepository.findByIdWithMembers(id)
		                                                  .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
		User savedUser = userRepository.save(newUser);
		Membership membership = membershipRepository.save(new Membership(savedUser, municipality, userRole));
		if (userRole == Role.YOUTH_COUNCIL_ADMINISTRATOR) {
			municipality.setHasPlatform(true);
		}
		municipality.addMember(newUser);
		municipalityRepository.save(municipality);
		logger.debug("Membership {}", membership);
		logger.debug("Membership User {}", membership.getUser());
		return membership.getUser();
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

	public boolean updateBanStatus(Long membershipId, boolean ban) {
		Membership membership = membershipRepository.findById(membershipId)
		                                            .orElseThrow(() -> new EntityNotFoundException("Membership not found"));
		if(!(membership.getUser().getRole()==Role.USER)){
			logger.debug("Moderator can only ban users, ban did not go through");
			return false;
		}
		membership.setBanned(ban);
		membershipRepository.save(membership);
		return true;
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

	public Membership getMembershipByIdAndEmail(Long id, String email) {
		Municipality municipality = municipalityRepository.findByIdWithMembers(id)
		                                                  .orElse(null);
		User user = userRepository.findByUsernameOrEmail(email)
		                          .orElse(null);
		if (municipality == null || user == null) {
			return null;
		}
		return membershipRepository.findByUserAndMunicipality(user.getId(), municipality.getId())
		                           .orElse(null);
	}

	public void update(Membership membership) {
		membershipRepository.save(membership);
	}
}
