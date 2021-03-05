package pl.fastus.spacestation.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "responses")
@Entity
public class IssPassesRequest {

    @Id
    @GeneratedValue
    private Long id;

    private double latitude;
    private double longitude;
    private Integer altitude;
    private Integer passes;
    private Long datetime;

    @OneToMany(mappedBy = "issPassesRequest",cascade = CascadeType.ALL)
    private Set<IssPasses> responses = new HashSet<>();

    public void addIssPasses(IssPasses passes){
        if(responses == null){
            responses = new HashSet<>();
        }
        responses.add( passes);
        passes.setIssPassesRequest( this );
    }
}
