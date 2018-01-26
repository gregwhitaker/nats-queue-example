# queue-client
Starts an example NATS client that subscribes to messages published to the `metrics-queue` queue in queue group `queuegroup1` and prints recevied messages to the console.

Start multiple clients to see them each receiving different messages.

## Running the Client
The client can be started by running the following command:

    $ ../gradlew :queue-client:run