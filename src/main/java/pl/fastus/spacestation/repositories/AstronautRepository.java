package pl.fastus.spacestation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fastus.spacestation.domain.Astronaut;

public interface AstronautRepository extends JpaRepository<Astronaut,String> {
}
