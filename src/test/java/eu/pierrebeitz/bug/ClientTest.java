package eu.pierrebeitz.bug;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

@QuarkusTest
class ClientTest {

    @Inject
    @RestClient
    Client client;

    @Test
    void aTest() {
    }

}