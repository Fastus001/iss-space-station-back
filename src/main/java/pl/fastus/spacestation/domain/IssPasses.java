package pl.fastus.spacestation.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "issPassesRequest",callSuper = false)
@ToString
@Builder
@Entity
public class IssPasses extends BaseEntity implements Comparable<IssPasses>{

    private Integer duration;
    private Long riseTime;

    @ManyToOne
    private IssPassesRequest issPassesRequest;

    @Override
    public int compareTo(IssPasses o) {
        return this.riseTime.compareTo( o.riseTime );
    }
}
