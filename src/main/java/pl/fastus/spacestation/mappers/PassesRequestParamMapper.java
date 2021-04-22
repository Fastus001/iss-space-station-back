package pl.fastus.spacestation.mappers;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.fastus.spacestation.domain.dto.PassesRequestDTO;

import java.util.List;

import static java.lang.String.valueOf;

public class PassesRequestParamMapper {

    public static MultiValueMap<String, String> getUriParams(PassesRequestDTO requestDTO){
        MultiValueMap<String, String> temp = new LinkedMultiValueMap<>();
        temp.put( "lat", List.of( valueOf( requestDTO.getLatitude() ) ) );
        temp.put( "lon", List.of( valueOf( requestDTO.getLongitude() ) ) );

        if(requestDTO.getAltitude()!=null){
            temp.put( "alt", List.of( valueOf( requestDTO.getAltitude() ) ) );
        }
        if ( requestDTO.getNumber() !=null ){
            temp.put( "n", List.of( valueOf(requestDTO.getNumber()) ) );
        }
        return temp;
    }
}
