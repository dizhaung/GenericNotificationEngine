DESCRIPTION

The Project consumes data from a Kafka topic using Apache Flink for processing. A stream coming from a Kafka topic is read , analysed by flink streaming and email notification event will be trigger based on the analysis done on json data. The result is printed to the console.

Concept is to publish json messages on kafka using flink kafka connector and in consumer those json data will be analysed by flink streaming and email notification event will be trigger based on the analysis done on json data. 

Technology used -
	a) JDK 1.8
	b) Flink 0.9.1
	c) zookeeper-3.4.12
	d) kafka_2.11-1.0.1
	e) Maven
  

Download Prerequisites:
	1.Download and install java8 from - https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
	2.Download and extract Zookeeper from - http://zookeeper.apache.org/releases.html
	3.Download and extract Kafka using from - http://kafka.apache.org/downloads.html

Installation:
	1.JDK Setup
		1. Install Java Jdk and add Environment Variables. 
			By opening Control Panel -> System -> Advanced system settings -> Environment Variables.
			Variable name : JAVA_HOME 
			Variable value: PATH of your JDK (Ex- C:\Program Files\Java\jdk1.8.0_92 )
		2. Search for a Path variable in the “System Variable” section in “Environment Variables” & Edit the path and type “;%JAVA_HOME%\bin” at the end of the 		text already written there.
		3. To confirm the Java installation just open cmd and type “java –version”, you should be able to see version of the java you just installed.

	2. Zookeeper Installation : 
		1. Goto your Zookeeper config directory. For me its C:\zookeeper-3.4.7\conf
		2. Rename file “zoo_sample.cfg” to “zoo.cfg”
		3. Open zoo.cfg in any text editor like notepad but I prefer notepad++.
		4. Find & edit dataDir=/tmp/zookeeper to :\zookeeper-3.4.7\data
		5. Add entry in System Environment Variables as we did for Java
			a. Add in System Variables ZOOKEEPER_HOME = C:\zookeeper-3.4.7
			b. Edit System Variable named “Path” add ;%ZOOKEEPER_HOME%\bin;
		6. You can change the default Zookeeper port in zoo.cfg file (Default port 2181).
		7. Run Zookeeper by opening a new cmd and type zkserver.
		8. You will see the command prompt with some details like the image below.

	3.Setting Up Kafka : 
		1. Go to your Kafka config directory. For me its C:\kafka_2.11-0.9.0.0\config
		2. Edit file “server.properties”
		3. Find & edit line “log.dirs=/tmp/kafka-logs” to “log.dir= C:\kafka_2.11-0.9.0.0\kafka-logs”.
		4. If your Zookeeper is running on some other machine or cluster you can edit “zookeeper.connect:2181” to your custom IP and port. For this demo we are 		using same machine so no need to change. Also Kafka port & broker.id are configurable in this file. Leave other settings as it is.
		5. Your Kafka will run on default port 9092 & connect to zookeeper’s default port which is 2181.
  
	4.Verify your topic is working for publisher and consumer by typing some messages on the producer console and see if you get it on the consumer console

	5.Before starting Flink kafka consumer and producer Please 
		1. create  this folder location - C:\FLINK\dataset
	   	2. copy these two file in this location
			a)config.properties
				a)Once copied, edit the file and enter your email-id & password
			b)events.csv
	6. RUNNING
		1. Before go for running the Flink job complete the above steps.....
		2. You can directly run the batch file or by running the runnable jar to start the flink job

 			a)Batch files
			  First we need to start the "Start-flink-kafka-consumer-emailAlert.bat"
			  Secondly we need to start "Start-flink-kafka-producer.bat"
