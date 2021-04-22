package pl.fastus.spacestation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fastus.spacestation.domain.StationNow;

public interface StationNowRepository extends JpaRepository<StationNow, Long> {

}
