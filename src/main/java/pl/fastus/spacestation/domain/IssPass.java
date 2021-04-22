package pl.fastus.spacestation.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class IssPass {
    @Id
    @GeneratedValue
    private Long id;

    private Integer duration;
    private Long riseTime;
}
