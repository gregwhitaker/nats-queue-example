# queue-service
Starts an example NATS service that publishes system metrics every second as a JSON object.

Example:

    {"id":"e215288f-c739-4823-ad0b-f807798fc07f","cpuPercentage":0.0,"totalPhysicalMemory":16384.0,"freePhysicalMemory":373.0}
        
## Running the Service
You can run the queue example service using the following command:

    $ ../gradlew :queue-service:run