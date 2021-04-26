package pl.fastus.spacestation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.dto.RequestDTO;
import pl.fastus.spacestation.domain.dto.ResponseDTO;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IssPassesMapper {

    @Mappings( {
            @Mapping( target = "datetime", source = "source.dateTime"),
            @Mapping( target = "responses.duration", source = "responses")
    } )
    IssPassesRequest toIssPassesRequest(RequestDTO source, List<ResponseDTO> responses);

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
