package pl.fastus.spacestation.services;

import org.springframework.util.MultiValueMap;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.StationNow;

public interface IssService {

     StationNow getIssNow();

     IssPassesRequest createIssPassesRequest(MultiValueMap<String, String> params);
}
