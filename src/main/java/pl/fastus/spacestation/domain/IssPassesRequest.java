package pl.fastus.spacestation.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.SortedSet;
import java.util.TreeSet;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "responses", callSuper = false)
@Entity
public class IssPassesRequest  extends BaseEntity{

    private double latitude;
    private double longitude;
    private Integer altitude;
    private Integer passes;
    private Long datetime;

    @OrderBy("sort")
    @OneToMany(mappedBy = "issPassesRequest",cascade = CascadeType.ALL)
    private SortedSet<IssPasses> responses = new TreeSet<>();
}
