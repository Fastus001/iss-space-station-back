package pl.fastus.spacestation.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.StationNowDTO;

@Mapper(componentModel = "spring")
public interface StationMapper {

    StationMapper INSTANCE = Mappers.getMapper( StationMapper.class );

    @Mappings( {
            @Mapping( target = "position", source = "issPositionDTO"),
            @Mapping( target = "timeStamp", source = "timestamp")
    } )
    StationNow stationNowDTOtoStationNow(StationNowDTO stationNowDTO);
}
