package pl.fastus.spacestation.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "issPassesRequest",callSuper = true)
@Builder
@Entity
public class IssPasses extends BaseEntity{

    private Long riseTime;
    private int duration;

    @ManyToOne
    private IssPassesRequest issPassesRequest;
}
