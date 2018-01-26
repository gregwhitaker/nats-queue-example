# nats-queue-example

An example of using [NATS](https://nats.io) for queue messaging.

This example starts a service that publishes CPU metrics every second to a `metrics-queue` subject and queue group `queuegroup1`. The queue is subscribed to by one-to-many clients and each message is received by only a single client.

## Background
NATS supports message queueing using point-to-point (one-to-one) communication.

To create a message queue, subscribers register a queue name. All subscribers with the same queue name form the queue group. As messages on the registered subject are published, one member of the group is chosen randomly to receive the message. Although queue groups have multiple subscribers, each message is consumed by only one.

Queue subscribers can be asynchronous, in which case the message handler callback function processes the delivered message. Synchronous queue subscribers must build in logic to processes the message.

More information can be found in the [NATS Documentation](https://nats.io/documentation/concepts/nats-queueing/).

## Prerequisites
The examples require a local NATS server to be running. To start a NATS server as a Docker container run the following commands:

    $ docker pull nats
    $ docker run -p 4222:4222 -p 6222:6222 -p 8222:8222 -d --name nats-main nats

## Running the Example
### Start the Queue Client
You can start the [Queue Client](queue-client/README.md) using the following command:

    $ ./gradlew :queue-client:run
    
Once the client and service is running you will see messages similar to the following in the terminal:

    [main] INFO nats.example.queue.client.Main - Starting NATS Example Queue Client
    [jnats-subscriptions] INFO nats.example.queue.client.Main - Received Message: "{\"id\":\"d2a47686-38de-4f34-9ed8-f2088ea190a6\",\"cpuPercentage\":0.06,\"totalPhysicalMemory\":16384.0,\"freePhysicalMemory\":244.0}"
    [jnats-subscriptions] INFO nats.example.queue.client.Main - Received Message: "{\"id\":\"aab40392-e716-4546-9cd9-472d150a74d8\",\"cpuPercentage\":0.06,\"totalPhysicalMemory\":16384.0,\"freePhysicalMemory\":275.0}"
    [jnats-subscriptions] INFO nats.example.queue.client.Main - Received Message: "{\"id\":\"9fa01ff2-0e82-45f0-aa77-53321a2a6635\",\"cpuPercentage\":0.06,\"totalPhysicalMemory\":16384.0,\"freePhysicalMemory\":221.0}"

You can start multiple clients to verify that each message produced by the service is only being received by a single client.

### Start the Queue Service
You can start the [Queue Service](queue-service/README.md) using the following command:

    $ ./gradlew :queue-service:run

Once the service is running you will see messages similar to the following in the terminal:

    [main] INFO nats.example.queue.service.Main - Starting NATS Example Queue Service
    [Timer-0] INFO nats.example.queue.service.MetricsPublishTask - Publishing: {"id":"e215288f-c739-4823-ad0b-f807798fc07f","cpuPercentage":0.0,"totalPhysicalMemory":16384.0,"freePhysicalMemory":373.0}
    [Timer-0] INFO nats.example.queue.service.MetricsPublishTask - Publishing: {"id":"536f3274-5fca-4041-9fe0-dc3f68190eaf","cpuPercentage":0.24,"totalPhysicalMemory":16384.0,"freePhysicalMemory":273.0}
    [Timer-0] INFO nats.example.queue.service.MetricsPublishTask - Publishing: {"id":"be451f72-4224-412d-81e0-d4784183e6f4","cpuPercentage":0.04,"totalPhysicalMemory":16384.0,"freePhysicalMemory":349.0}

## Bugs and Feedback
For bugs, questions and discussions please use the [Github Issues](https://github.com/gregwhitaker/nats-queue-example/issues).

## License
MIT License

Copyright (c) 2018 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.