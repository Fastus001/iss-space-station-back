package pl.fastus.spacestation.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "issPassesRequest",callSuper = true)
@ToString
@Builder
@Entity
public class IssPasses extends BaseEntity{

    private Integer duration;
    private Long riseTime;

    @ManyToOne
    private IssPassesRequest issPassesRequest;
}
