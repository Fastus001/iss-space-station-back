package pl.fastus.spacestation.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.Request;

@Mapper(componentModel = "spring")
public interface RequestMapper {


    @Mappings( {
            @Mapping( target = "datetime", source = "datetime"),
            @Mapping( target = "issPasses.responses", ignore = true)
    } )
    IssPassesRequest requestToPassesRequest(Request source);
}
