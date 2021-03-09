package pl.fastus.spacestation.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "position",callSuper = false)
@Entity
public class StationNow extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    private Position position;

    private Long timeStamp;

    @Builder
    public StationNow(Long id, Position position, Long timeStamp) {
        super( id );
        this.position = position;
        this.timeStamp = timeStamp;
    }
}
