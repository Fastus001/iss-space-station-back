package pl.fastus.spacestation.services;

import org.junit.jupiter.api.Test;
import pl.fastus.spacestation.domain.IssNow;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class OkHttpServiceImplTest {
    OkHttpServiceImpl service;

    @Test
    void getIssNow() {
        service = new OkHttpServiceImpl();

        final IssNow issNow = service.getIssNow();

        assertNotNull( issNow );
    }
}