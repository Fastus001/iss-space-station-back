package pl.fastus.spacestation.services;

import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.StationNowDTO;

public interface StationNowService{

    StationNow save(StationNowDTO stationNowToSave);
}
