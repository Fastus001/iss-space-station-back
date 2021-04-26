package pl.fastus.spacestation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;

@Mapper(componentModel = "spring")
public interface IssPassesMapper {

    @Mappings( {
            @Mapping( target = "datetime", source = "source.requestDTO.dateTime"),
            @Mapping( target = "latitude", source = "source.requestDTO.latitude"),
            @Mapping( target = "longitude", source = "source.requestDTO.longitude"),
            @Mapping( target = "altitude", source = "source.requestDTO.altitude"),
            @Mapping( target = "passes", source = "source.requestDTO.passes"),
            @Mapping( target = "responses", source = "source.responseDTO")
    } )
    IssPassesRequest toIssPassesRequest(StationPassesRequestDTO source);

    @Mappings({
            @Mapping(source = "request.latitude", target = "requestDTO.latitude"),
            @Mapping(source = "request.longitude", target = "requestDTO.longitude"),
            @Mapping(source = "request.altitude", target = "requestDTO.altitude"),
            @Mapping(source = "request.passes", target = "requestDTO.passes"),
            @Mapping(source = "request.datetime", target = "requestDTO.dateTime"),
            @Mapping(source = "responses", target = "responseDTO")
    })
    StationPassesRequestDTO issPassesRequestToStationPassesRequestDTO(IssPassesRequest request);

}
