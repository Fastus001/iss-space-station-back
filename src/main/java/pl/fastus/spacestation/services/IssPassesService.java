package pl.fastus.spacestation.services;

import org.springframework.stereotype.Service;
import pl.fastus.spacestation.repositories.IssPassesRepository;

@Service
public class IssPassesService {

    private final IssPassesRepository repository;

    public IssPassesService(IssPassesRepository repository) {
        this.repository = repository;
    }

}
