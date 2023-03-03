package be.kdg.finalproject.domain.location;

import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.domain.user.YouthCouncil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "municipalities")
@Getter
@Setter
@NoArgsConstructor
public class Municipality {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "municipalitiy_id", nullable = false)
	private Long id;

	@Column (name = "name", nullable = false)
	private String name;

	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "youth_council_id")
	private YouthCouncil youthCouncil;

	@ManyToMany (mappedBy = "municipalities", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<>();

	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PostCode> postcodes = new HashSet<>();

	public Municipality(String name) {
		this.name = name;
	}

	public Municipality(String name, Set<PostCode> postcodes) {
		this.name = name;
		this.postcodes = postcodes;
	}
}
