package pl.fastus.spacestation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IssPosition {

    @Id
    @GeneratedValue
    private Long id;

    private double latitude;
    private double longitude;
}
