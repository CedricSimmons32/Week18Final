package nfl.teams.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nfl.teams.controller.model.LocationData;
import nfl.teams.dao.LocationDao;
import nfl.teams.entity.Location;

@Service
public class TeamsService {
	
	@Autowired
	private LocationDao locationDao;
	
	@Transactional(readOnly = false)
	public LocationData saveLocation(LocationData locationData) {
		Location location = locationData.toLocation();
		Location dbLocation = locationDao.save(location);
		
		return new LocationData(dbLocation);
		
	}

	@Transactional(readOnly = true)
	public LocationData retrieveLocationById(Long locationId) {
		Location location = findLocationById(locationId);
		return new LocationData(location);
	}

	private Location findLocationById(Long locationId) {
		return locationDao.findById(locationId).orElseThrow(() -> new NoSuchElementException
				("location with ID=" + locationId + "was not Found"));
		
	}

	
	

	@Transactional(readOnly = true)
	public List<LocationData> retrieveAllLocations() {
		List<Location> locationTeams = locationDao.findAll();
		List<LocationData> locationNfl = new LinkedList<>();
		
		locationTeams.sort((loc1, loc2) -> loc1.getLocationId().compareTo(loc2.getLocationId()));
		
	for(Location location : locationTeams) {
			LocationData locationdata = new LocationData(location);
			locationNfl.add(locationdata);
		}
		
		return locationNfl;
		//@formatter: off
//	  return locationDao.findAll().
//			  stream()
//			  .map(loc -> new LocationData(loc))
//			  .toList();
//	  
//	  return locationDao.findAll()
//			  .stream()
//			  .sorted((loc1, loc2) ->
//			  	loc1.getLocationId().compareTo(loc2.getLocationId()))
//			  .map(LocationData::new)
//			  .toList();
	  //@formatter: on
	}

	@Transactional(readOnly = false)
	public void deleteLocation(Long locationId) {
		Location location = findLocationById(locationId);
		locationDao.delete(location);
	}

}
