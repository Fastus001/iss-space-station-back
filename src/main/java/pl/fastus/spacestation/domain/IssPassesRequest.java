package pl.fastus.spacestation.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "responses")
@Entity
public class IssPassesRequest {

    @Id
    @GeneratedValue
    private Long id;

    private Integer latitude;
    private Integer longitude;
    private Integer altitude;
    private Integer numberOfPasses;
    private Long requestTimestamp;

    @OneToOne
    private Set<IssPasses> responses = new HashSet<>();
}
