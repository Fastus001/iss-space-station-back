package pl.fastus.spacestation.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "issPosition")
@Entity
public class IssNow {

    @Id
    @GeneratedValue
    private Long id;

    private Long timeStamp;

    @OneToOne(cascade = CascadeType.ALL)
    private IssPosition issPosition;
}
