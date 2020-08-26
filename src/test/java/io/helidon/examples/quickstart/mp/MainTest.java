
package io.helidon.examples.quickstart.mp;

import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import io.helidon.microprofile.server.Server;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    private static Server server;

    @BeforeAll
    static void startTheServer() {
        server = Server.create().start();
    }

    @AfterAll
    static void stopTheServer() {
        server.stop();
    }

    @Test
    void testVaccinated() {
        Client client = ClientBuilder.newClient();

        JsonValue jsonValue = client
                .target(getConnectionString("/vaccinated/Dino"))
                .request()
                .get(JsonValue.class);
        assertEquals(JsonValue.TRUE, jsonValue, "Dino should be vaccinated");

        jsonValue = client
                .target(getConnectionString("/vaccinated/Hoppy"))
                .request()
                .get(JsonValue.class);
        assertEquals(JsonValue.TRUE, jsonValue, "Hoppy should be vaccinated");

        jsonValue = client
                .target(getConnectionString("/vaccinated/Baby Puss"))
                .request()
                .get(JsonValue.class);
        assertEquals(JsonValue.FALSE, jsonValue, "Baby Puss should not be vaccinated");
    }

    private String getConnectionString(String path) {
        return "http://localhost:" + server.port() + path;
    }
}
