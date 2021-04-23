package pl.fastus.spacestation.mappers;

import org.mapstruct.Mapper;
import pl.fastus.spacestation.domain.Astronaut;
import pl.fastus.spacestation.domain.dto.PeopleDTO;

@Mapper(componentModel = "spring")
public interface AstronautMapper {

    Astronaut peopleDTOToAstronaut(PeopleDTO peopleDTO);
}
