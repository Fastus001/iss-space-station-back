package pl.fastus.spacestation.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.fastus.spacestation.domain.Position;

public interface IssPositionRepository extends CrudRepository<Position, Long> {

}
