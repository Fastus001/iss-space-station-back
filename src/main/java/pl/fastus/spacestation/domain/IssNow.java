package pl.fastus.spacestation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IssNow {

    @Id
    @GeneratedValue
    private Long id;

    private Long timeStamp;

    @OneToOne
    private IssPosition issPosition;
}
