package pl.fastus.spacestation.services;

import pl.fastus.spacestation.domain.IssNow;
import pl.fastus.spacestation.domain.IssPassesRequest;

public interface OkHttpService {

     IssNow getIssNow();

     IssPassesRequest createIssPassesRequest(String command);
}
