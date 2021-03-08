package pl.fastus.spacestation.services;

import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.IssPassesRequest;

public interface OkHttpService {

     StationNow getIssNow();

     IssPassesRequest createIssPassesRequest(String command);
}
