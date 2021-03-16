package pl.fastus.spacestation.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.StationNowDTO;

@Mapper(componentModel = "spring")
public interface StationMapper {

    @Mappings( {
            @Mapping( target = "position", source = "issPosition"),
            @Mapping( target = "timeStamp", source = "timestamp")
    } )
    StationNow stationNowDTOtoStationNow(StationNowDTO stationNowDTO);
}
