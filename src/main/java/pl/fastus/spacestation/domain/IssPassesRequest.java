package pl.fastus.spacestation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(exclude = "responses")
@Entity
public class IssPassesRequest{

    @Id
    @GeneratedValue
    private Long id;

    private double latitude;
    private double longitude;
    private Integer altitude;
    private Integer passes;
    private Long datetime;


    @OneToMany(cascade = CascadeType.ALL)
    private Set<IssPass> responses = new HashSet<>();
}
