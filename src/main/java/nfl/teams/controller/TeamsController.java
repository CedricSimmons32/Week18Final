package nfl.teams.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import nfl.teams.controller.model.LocationData;
import nfl.teams.service.TeamsService;

@RestController
@RequestMapping("/nfl_teams")
@Slf4j
public class TeamsController {
	@Autowired
	private TeamsService teamsService;
	
	@PostMapping("/location")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LocationData createLocation(@RequestBody LocationData locationData) {
		log.info("Creating location ()", locationData);
		return teamsService.saveLocation(locationData);
	}
	
	@PutMapping("/location/{locationId}")
	public LocationData updateLocation(@PathVariable Long locationId,
			@RequestBody LocationData locationData) {
		locationData.setLocationId(locationId);
		log.info("Updating location ()", locationData);
		return teamsService.saveLocation(locationData);
	}
	
	@GetMapping("/location/{locationId}")
	public LocationData retrieveLocation(@PathVariable Long locationId) {
		log.info("Retrieving location with ID={}", locationId);
		return teamsService.retrieveLocationById(locationId);
	}
	
	@GetMapping("/location")
	public List<LocationData> retrieveAllLocations() {
		log.info("Retrieving all locations");
		return teamsService.retrieveAllLocations();
	}
	
	@DeleteMapping("/location/{locationId}")
	public Map<String, String> deletelocation (@PathVariable Long locationId) {
	log.info("Delete location with ID=" + locationId + ".");
	teamsService.deleteLocation(locationId);
	
	return Map.of("NFL message", "NFL location with ID=" + locationId + "was deleted." );
}
}