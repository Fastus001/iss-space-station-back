package pl.fastus.spacestation.services;

import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.StationNow;


@Service
public interface IssNowService extends CrudService<StationNow, Long>{
}
