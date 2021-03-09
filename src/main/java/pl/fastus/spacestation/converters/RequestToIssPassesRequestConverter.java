package pl.fastus.spacestation.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.Request;

/**
 * Created by Tom - 06.03.2021
 */
@Component
public class RequestToIssPassesRequestConverter implements Converter<Request, IssPassesRequest> {

    @Override
    public IssPassesRequest convert(Request request) {
        return IssPassesRequest.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .altitude(request.getAltitude())
                .datetime( request.getDatetime() )
                .passes(request.getPasses()).build();
    }
}
