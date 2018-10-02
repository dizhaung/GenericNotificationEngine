@echo on
start java -jar flink-kafka-consumer-emailAlert.jar --topic streamstest --bootstrap.servers localhost:9092 --zookeeper.connect localhost:2181 --group.id myGroup
