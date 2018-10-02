@echo off
start java -jar flink-kafka-producer.jar --topic streamstest --bootstrap.servers localhost:9092
