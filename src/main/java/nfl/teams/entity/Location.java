package nfl.teams.entity;


import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.HashSet;


@Entity
@Data
public class Location {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long locationId;
	private String teamAddress;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Player> players = new HashSet<>();

}
