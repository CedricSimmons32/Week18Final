package nfl.teams.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import nfl.teams.nflTeamsApplication;
import nfl.teams.controller.model.LocationData;


@SpringBootTest(webEnvironment = WebEnvironment.NONE, 
classes = nflTeamsApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")
class TeamsControllerTest extends TeamsServiceTestSupport{
	
	@Test
	void testInsertLocation() {
		//Given: location request.
		LocationData request = buildInsertLocation(1);
		LocationData expected = buildInsertLocation(1);
		
		//When: the location is added to the location table.
		LocationData actual = insertLocation(request);
		
		//Then: the location is returned is what is expected.
		assertThat(actual).isEqualTo(expected);
		
		//And: there is one row in the location table. 
		assertThat(rowsInLocationTable()).isOne();
	}
	
	@Test
	void testRetrieveLocation() {
		//Given a location
		LocationData location = insertLocation(buildInsertLocation(1));
		LocationData expected = buildInsertLocation(1);
		
		//When: The location is retrieved by Location ID
		LocationData actual = retrieveLocation(location.getLocationId());
		
		//Then: the actual location is equal to the expected location.
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void testRetrieveAllLocations() {
		//Given: two locations
		List<LocationData> expected = insertTwolocation();
		
		//when: all locations are retrieved
		List<LocationData> actual = retrieveAllLocations();
				
		//Then: the retrieve locations expected to be the same
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void teamsUpdateLocation() {
	    //given: a location and update request
		insertLocation(buildInsertLocation(1));
		LocationData expected = buildUpdateLocation();
		
		//When: the location is updated
		LocationData actual = updateLocation(expected);
		
		//Then: the location is returned as expected
		assertThat(actual).isEqualTo(expected);
		
		//And: there is one row on the location table
		assertThat(rowsInLocationTable()).isOne();
	}

	
	@Test
	void testDeleteLocationWithPlayers () {
		//given a location for 2 players
		LocationData location = insertLocation(buildInsertLocation(1));
		Long locationId = location.getLocationId();
		
		insertPlayer(1);
		insertPlayer(2);
		
		assertThat(rowsInLocationTable()).isOne();
		assertThat(rowsInPlayerTable()).isEqualTo(2);
		assertThat(rowsInPlayerCoachTable()).isEqualTo(2);
		
		int coachRows = rowsInCoachTable();
		//when: the location is deleted
		deleteLocation(locationId);
		
		//then: there is no location,player or player_coach rows
		assertThat(rowsInLocationTable()).isZero();
		assertThat(rowsInPlayerTable()).isZero();
		assertThat(rowsInPlayerCoachTable()).isZero();
		
		//and the coach rows have not changed
		assertThat(rowsInCoachTable()).isEqualTo(coachRows);
	}

	

	
	


	

}
