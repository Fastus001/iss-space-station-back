package pl.fastus.spacestation.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.fastus.spacestation.domain.StationNow;

public interface IssNowRepository extends CrudRepository<StationNow, Long> {

}
