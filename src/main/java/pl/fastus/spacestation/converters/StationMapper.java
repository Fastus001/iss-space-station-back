package pl.fastus.spacestation.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.Request;
import pl.fastus.spacestation.domain.dto.Response;
import pl.fastus.spacestation.domain.dto.StationNowDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationMapper {


    @Mappings( {
            @Mapping( target = "datetime", source = "source.datetime"),
            @Mapping( target = "responses.duration", source = "responses"),
            @Mapping( target = "responses.risetime", source = "responses")
    } )
    IssPassesRequest toIssPassesRequest(Request source, List<Response> responses);


    @Mappings( {
            @Mapping( target = "position", source = "issPosition"),
            @Mapping( target = "timeStamp", source = "timestamp")
    } )
    StationNow toStationNow(StationNowDTO stationNowDTO);
}
