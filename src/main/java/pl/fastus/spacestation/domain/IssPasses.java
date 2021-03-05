package pl.fastus.spacestation.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "issPassesRequest")
@Entity
public class IssPasses {

    @Id
    @GeneratedValue
    private Long id;

    private Long riseTime;
    private int duration;

    @ManyToMany(mappedBy = "responses")
    private IssPassesRequest issPassesRequest;
}
