package pl.fastus.spacestation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.dto.RequestDTO;
import pl.fastus.spacestation.domain.dto.ResponseDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IssPassesMapper {

    IssPassesMapper INSTANCE = Mappers.getMapper( IssPassesMapper.class );

    @Mappings( {
            @Mapping( target = "datetime", source = "source.dateTime"),
            @Mapping( target = "responses.duration", source = "responses")
    } )
    IssPassesRequest toIssPassesRequest(RequestDTO source, List<ResponseDTO> responses);

}
