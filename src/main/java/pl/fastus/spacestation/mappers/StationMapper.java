package pl.fastus.spacestation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.StationNowDTO;

@Mapper(componentModel = "spring")
public interface StationMapper {
    @Mappings( {
            @Mapping( target = "latitude", source = "issPositionDTO.latitude"),
            @Mapping( target = "longitude", source = "issPositionDTO.longitude"),
            @Mapping( target = "timeStamp", source = "timestamp")
    } )
    StationNow stationNowDTOtoStationNow(StationNowDTO stationNowDTO);
}
