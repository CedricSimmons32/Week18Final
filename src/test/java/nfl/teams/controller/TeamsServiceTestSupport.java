package nfl.teams.controller;

import java.util.List;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import nfl.teams.controller.model.LocationData;
import nfl.teams.entity.Location;

public class TeamsServiceTestSupport {
	
	private static final String PLAYER_TABLE = "player";
	private static final String PLAYER_COACH = "player_coach";
	private static final String COACH_TABLE = "coach";
	private static final String LOCATION_TABLE = "location";
	
	private static final String INSERT_PLAYER_1_SQL = """ 
			INSERT INTO player 
			(player_id, name, player_position, age)
			VALUES (43245, 'Patrick Mahomes', 'QB', 24)
			""";
	
	private static final String INSERT_PLAYER_2_SQL = """ 
			INSERT INTO player
			(player_id, name, player_position, age)
			VALUES (33123, 'Justin Jefferson', 'WR', 22)
			""";
	
	private static final String INSERT_COACH_1_SQL = """
			INSERT INTO player_coach 
			(player_id, coach_id)
			VALUES (1, 456), (1, 32)
			""";
	
	private static final String INSERT_COACH_2_SQL = """
			INSERT INTO player_coach 
			(player_id, coach_id)
			VALUES (2, 790), (2,690)
			""";
	//formatter:off
	private LocationData insertAddress1 = new LocationData (
			1L,
			"Rockslide North Ave.",
			"2234 North solid Pine grove Rd.",
			"Las Vegas",
			"Nevada",
			"43234",
			"(453)567-4322"
	);
	
	private LocationData insertAddress2 = new LocationData (
			2L,
			"CheeseWheel South Hwy.",
			"4532 Fosters Ferry Rd.",
			"Green Bay",
			"Wisconsin",
			"76532",
			"(234)767-8432"
	);
	
	private LocationData updateTeamAddress1 = new LocationData(
			1L,
			"MountainValley West Ave.",
			"543 West Valley Mtn Rd.",
			"Los Angeles",
			"California",
			"67543",
			"(321)987-2000"
	);
	
	

	//formatter:on
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TeamsController teamsController;
	
	protected LocationData buildInsertLocation(int which) {
		// TODO Auto-generated method stub
		return which == 1 ? insertAddress1 : insertAddress2;
	}
	
	protected int rowsInLocationTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, LOCATION_TABLE);
	}

	protected LocationData insertLocation(LocationData locationData) {
		Location location = locationData.toLocation();
		LocationData clone = new LocationData(location);
		
		clone.setLocationId(null);
		return teamsController.createLocation(clone);
	}
	
	protected LocationData retrieveLocation(Long locationId) {
		return teamsController.retrieveLocation(locationId);
	}
	
	protected List<LocationData> insertTwolocation() {
		LocationData location1 = insertLocation(buildInsertLocation(1));
		LocationData location2 = insertLocation(buildInsertLocation(2));
		
		return List.of(location1, location2);
	}
	
	protected List<LocationData> retrieveAllLocations() {
		return teamsController.retrieveAllLocations();
	}
	
	protected List<LocationData> sorted(List<LocationData> list) {
		List<LocationData> data = new LinkedList<>(list);
		
		data.sort(
			(loc1, loc2) -> (int)(loc1.getLocationId() - loc2.getLocationId()));
		
		return data;
	}
	
	protected LocationData updateLocation(LocationData locationData) {
		return teamsController.updateLocation(locationData.getLocationId(), locationData);
	}

	protected LocationData buildUpdateLocation() {
		return updateTeamAddress1;
	}
	
	protected void insertPlayer(int which) {
		String playerSql = which == 1 ? INSERT_PLAYER_1_SQL : INSERT_PLAYER_2_SQL;
		String coachSql = which == 1 ? INSERT_COACH_1_SQL : INSERT_COACH_2_SQL;
		
		jdbcTemplate.update(playerSql);
		jdbcTemplate.update(coachSql);
	}
	protected int rowsInCoachTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, COACH_TABLE);
	}

	protected int rowsInPlayerCoachTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, PLAYER_COACH);
	}

	protected int rowsInPlayerTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, PLAYER_TABLE);            
	}
	
	protected void deleteLocation(Long locationId) {
		teamsController.deletelocation(locationId);
		
	}

}
