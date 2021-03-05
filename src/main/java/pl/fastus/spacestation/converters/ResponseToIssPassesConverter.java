package pl.fastus.spacestation.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.fastus.spacestation.domain.IssPasses;
import pl.fastus.spacestation.domain.Response;

/**
 * Created by Tom - 06.03.2021
 */
@Component
public class ResponseToIssPassesConverter implements Converter<Response, IssPasses> {

    @Override
    public IssPasses convert(Response response) {

        return IssPasses.builder()
                .duration(response.getDuration())
                .riseTime(Long.valueOf(response.getRisetime())).build();
    }
}
