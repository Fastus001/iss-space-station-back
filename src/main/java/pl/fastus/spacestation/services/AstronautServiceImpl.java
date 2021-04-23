package pl.fastus.spacestation.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.Astronaut;
import pl.fastus.spacestation.domain.dto.AstronautsDTO;
import pl.fastus.spacestation.mappers.AstronautMapper;
import pl.fastus.spacestation.repositories.AstronautRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class AstronautServiceImpl {

    private final AstronautRepository repository;
    private final AstronautMapper astronautMapper;

    public List<Astronaut> saveAstros(AstronautsDTO astronautsDTO){
        return astronautsDTO.getPeople()
                .stream()
                .map(astronautMapper::peopleDTOToAstronaut)
                .map(this::saveOrUpdate)
                .collect(Collectors.toList());
    }

    @Transactional
    public Astronaut saveOrUpdate(Astronaut astronaut){
        Optional<Astronaut> optionalAstronaut = repository.findById(astronaut.getName());
        if(optionalAstronaut.isEmpty()){
            return repository.save(astronaut);
        }
        Astronaut astroToUpdate = optionalAstronaut.get();
        astroToUpdate.setUpdateTime(LocalDateTime.now());
        return astroToUpdate;
    }
}
