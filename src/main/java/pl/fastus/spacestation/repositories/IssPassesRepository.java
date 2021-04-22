package pl.fastus.spacestation.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.fastus.spacestation.domain.IssPass;

public interface IssPassesRepository extends CrudRepository<IssPass, Long> {

}
