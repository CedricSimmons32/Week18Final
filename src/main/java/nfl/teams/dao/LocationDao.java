package nfl.teams.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import nfl.teams.entity.Location;

public interface LocationDao extends JpaRepository<Location, Long> {

}
