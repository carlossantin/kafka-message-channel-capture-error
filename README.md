# KAFKA-MESSAGE-CHANNEL-CAPTURE-ERROR

The goal of this project is to study how to capture exceptions thrown by KafkaProducer when executed in asynchronous mode. 

When using MessageChannel we can reach a state where the KafkaProducer is not able to publish an event
due to an unavailable broker, for example. Since it opens a new thread to perform the send operation, a success
response can be returned to caller. So, we have to identify that this exception have occurred to perform some treatment.

## Usage:

You must start a docker cluster. To do this, run the docker-compose file.
It is necessary to have **docker** and **docker-compose** installed in your machine.

Starting Docker:
```shell
sudo systemctl start docker 
```

Starting containers:
```shell
sudo docker-compose up -d
```

Run the projet using IntelliJ or gradlew (use java 11):
```shell
./gradlew clean build
./gradlew bootRun
```

Assuming that you have Kafka console in your machine, describe the output topic to check who is his leader of it:
```shell
$KAFKA_HOME/bin/kafka-topics.sh --zookeeper localhost:22181 --create --describe --topic output-topic-v1
```

Stop the leader assigned to the output topic. For example, if the leader is 1:
```shell
docker stop multiple_brokers_kafka-1_1
```

Publish a message in input topic having the following payload:
```json
{"id":  1}
```
```shell
$KAFKA_HOME/bin/kafka-console-producer.sh --broker-list localhost:29092 --topic input-topic-v1
```

Wait for 30s and you will get an exception from KafkaProducer


