package nfl.teams.entity;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;


@Entity
@Data
public class Coach {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long coachId;
	
	private String name;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany (mappedBy = "coaches")
	private Set<Player> players = new HashSet<>();
}
