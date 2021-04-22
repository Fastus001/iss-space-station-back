package pl.fastus.spacestation.services;

import pl.fastus.spacestation.domain.dto.PassesRequestDTO;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;

public interface IssPassesRequestService {

    StationPassesRequestDTO saveDTO(PassesRequestDTO toSave);

    StationPassesRequestDTO findById(Long id);
}
