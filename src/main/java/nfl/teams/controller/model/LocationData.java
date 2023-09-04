package nfl.teams.controller.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import nfl.teams.entity.Coach;
import nfl.teams.entity.Location;
import nfl.teams.entity.Player;

@Data
@NoArgsConstructor
public class LocationData {
	
	private Long locationId;
	private String teamAddress;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	private Set<PlayerData> players = new HashSet<>();
	
	public LocationData(Location location) {
		this.locationId = location.getLocationId();
		this.teamAddress = location.getTeamAddress();
		this.streetAddress = location.getStreetAddress();
		this.city = location.getCity();
		this.state = location.getState();
		this.zip = location.getZip();
		this.phone = location.getPhone();
		
		for(Player player : location.getPlayers()) {
			this.players.add(new PlayerData(player));
		}
	}
	
	public LocationData( Long locationId, String teamAddress, String streetAddress, String city,
			String state, String zip, String phone) {
			
			this.locationId = locationId;
			this.teamAddress = teamAddress;
			this.streetAddress = streetAddress;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.phone = phone;
	}
	
	public Location toLocation() {
		Location location = new Location();
		
		location.setLocationId(locationId);
		location.setTeamAddress(teamAddress);
		location.setStreetAddress(streetAddress);
		location.setCity(city);
		location.setState(state);
		location.setZip(zip);
		location.setPhone(phone);
		
		for(PlayerData playerdata : players) {
			location.getPlayers().add(playerdata.toPlayer());
		}
		return location;
	}
	
	@Data
	@NoArgsConstructor
	public class PlayerData { 
		private Long playerId;
		private String name;
		private String playerPosition;
		private int age;
		private Set<CoachData> coaches = new HashSet<>();
		
		public PlayerData (Player player) {
			this.playerId = player.getPlayerId();
			this.name = player.getName();
			this.playerPosition = player.getPlayerPosition();
			this.age = player.getAge();
			
			for(Coach coach : player.getCoaches()) {
				this.coaches.add(new CoachData(coach));
			}
		}
		
		public Player toPlayer() {
			Player player = new Player();
			
			player.setPlayerId(playerId);
			player.setName(name);
			player.setPlayerPosition(playerPosition);
			player.setAge(age);
			
			for(CoachData coachData : coaches) {
			  player.getCoaches().add(coachData.toCoach());
			}
			
			return player;
		}
	}
		
		@Data
		@NoArgsConstructor
		public class CoachData {
			private Long coachId; 
			private String name;
			
		public CoachData(Coach coach) {
			this.coachId = coach.getCoachId();
			this.name = coach.getName();
		}
		
		public Coach toCoach () {
			Coach coach = new Coach();
			
			coach.setCoachId(coachId);
			coach.setName(name);
			
			return coach;
				}
			}
		}