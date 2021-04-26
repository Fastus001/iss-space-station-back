package pl.fastus.spacestation.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.fastus.spacestation.domain.IssPassesRequest;


public interface StationPassesRequestRepository extends CrudRepository<IssPassesRequest, Long> {

}
