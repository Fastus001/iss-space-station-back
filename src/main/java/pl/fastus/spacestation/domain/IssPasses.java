package pl.fastus.spacestation.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "issPassesRequest")
@Builder
@Entity
public class IssPasses {

    @Id
    @GeneratedValue
    private Long id;

    private Long riseTime;
    private int duration;

    @ManyToOne
    private IssPassesRequest issPassesRequest;
}
