package nats.example.queue.client;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Starts the NATS Example Queue Client.
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws Exception {
        LOGGER.info("Starting NATS Example Queue Client");

        Connection conn = Nats.connect("nats://localhost:4222");

        conn.subscribe("metrics-queue", "queuegroup1", message -> {
            LOGGER.info("Received Message: " + new String(message.getData()));
        });
    }
}
