package pl.fastus.spacestation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IssNow {

    @Id
    @GeneratedValue
    private Long id;

    private Long timeStamp;

    @OneToOne(cascade = CascadeType.ALL)
    private IssPosition issPosition;
}
