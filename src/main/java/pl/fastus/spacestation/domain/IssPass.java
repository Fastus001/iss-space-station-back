package pl.fastus.spacestation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class IssPass {
    @Id
    @GeneratedValue
    private Long id;

    private Integer duration;
    private Long riseTime;
}
