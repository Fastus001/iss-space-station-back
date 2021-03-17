package pl.fastus.spacestation.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class AstronautsDTO {

    private String message;
    private int number;
    private List<PeopleDTO> people;
}
