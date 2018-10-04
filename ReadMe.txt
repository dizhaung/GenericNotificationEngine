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
		1. Install Java JDK and add Environment Variables. 
		Open Control Panel -> System -> Advanced system settings -> Environment Variables. And provide below in respective fields
			- Variable name = JAVA_HOME 
			- Variable value = PATH of your JDK (For me it's C:\Program Files\Java\jdk1.8.0_181)
		2. Search for a "Path" variable in the “System Variable” section in “Environment Variables” & Edit the path and type “;%JAVA_HOME%\bin” at the end of the text already written there.
		3. To confirm the Java installation just open cmd and type “java –version”, you should be able to see version of the java you just installed.

	2. Zookeeper Installation : 
		1. Goto your Zookeeper config directory. For me its C:\GenericNotificationEngine\KAFKA\zookeeper-3.4.12\zookeeper-3.4.12\conf
		2. Rename file “zoo_sample.cfg” to “zoo.cfg”
		3. Open zoo.cfg in any text editor like notepad but I prefer notepad++.
		4. Find & edit dataDir=/tmp/zookeeper to C:\GenericNotificationEngine\KAFKA\zookeeper-3.4.12\zookeeper-3.4.12\data
		5. Add entry in System Environment Variables as we did for Java
			a. Add in System Variables ZOOKEEPER_HOME=C:\GenericNotificationEngine\KAFKA\zookeeper-3.4.12\zookeeper-3.4.12\conf
			b. Edit System Variable named “Path” add ;%ZOOKEEPER_HOME%\bin;
		6. Go to C:\GenericNotificationEngine\KAFKA\zookeeper-3.4.12\zookeeper-3.4.12\bin
		7. Open a command prompt here by pressing Shift + right click and choose “Open command window here” option
		8. Now type "zkserver" and press Enter.
		9. You will see the command prompt with details that Zookeeper is running.

	3. Setting Up Kafka : 
		1. Go to your Kafka config directory. For me its C:\GenericNotificationEngine\KAFKA\kafka_2.11-1.0.1
		2. Edit file “server.properties”
		3. Find & edit line “log.dirs=/tmp/kafka-logs” to “log.dir=C:\WORK\KAFKA\kafka_2.11-1.0.1\kafka-logs”. And save the file
		4. If your Zookeeper is running on local machine and listening to port 2181, “zookeeper.connect:2181”.
		5. Your Kafka will run on default port 9092 & connect to zookeeper’s default port which is 2181.
	
	4. Running Kafka Server :
		Important: Please ensure that your Zookeeper instance is up and running before starting a Kafka server.
		1. Go to your Kafka installation directory C:\GenericNotificationEngine\KAFKA\kafka_2.11-1.0.1
		2. Open a command prompt here by pressing Shift + right click and choose“Open command window here” option)
		3. Now type .\bin\windows\kafka-server-start.bat .\config\server.properties and press and press Enter.
		4. Now your Kafka is up and running, you can create topics to store messages. Also we can produce or consume data.

	5. Create a topic
		  kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic streamstest

	6. Start a console producer
  		kafka-console-producer.bat --broker-list localhost:9092 --topic streamstest

	7. Start a console consumer
  		kafka-console-consumer.bat --zookeeper localhost:2181 --topic streamstest
  		Verify your topic is working for publisher and consumer
  
	8. Verify your topic is working for publisher and consumer by typing some messages on the producer console and see if you get it on the consumer console. If the message typed on producer console is reaching consumer console, the KAFKA is running fine.

	9. Before starting Flink kafka consumer and producer Please 
		1. create  this folder location - C:\FLINK\dataset
	   	2. copy these two file in this location
			a)config.properties
				a)Once copied, edit the file and enter email-id & password to be used to send emails.
			b)events.csv (contains some test records)
	
	10. Code setup & creation of runnable jars
		1. Open you Java IDE, I have used eclipse
		2. Import Java code for flink-kafka-consumer-emailAlert and create runnable JAR file.
			a. Click on File -> Import -> Existing Maven Project -> Click Next -> Select Root directory as "C:\GenericNotificationEngine\GNE-SourceCode\flink-kafka-consumer-emailAlerter" -> Select "tick-box" in "projects" and click on Next 
			b. Make sure all dependencies, class-path settings are properly included and there are no errors
			c. Do clean using Maven Clean option
			d. create runnable jars using File -> Export -> Runnable JAR File -> Select launch configuration "flink-kafka-consumer-emailAlert" and export destination as "C:\GenericNotificationEngine\RunnableJars\flink-kafka-consumer-emailAlert.jar"
		3. Import Java code for flink-kafka-consumer-emailAlert and create runnable JAR file.
			a. Click on File -> Import -> Existing Maven Project -> Click Next -> Select Root directory as "C:\GenericNotificationEngine\GNE-SourceCode\flink-kafka-producer" -> Select "tick-box" in "projects" and click on Next
			b. Make sure all dependencies, class-path settings are properly included and there are no errors
			c. Do clean using Maven Clean option
			d. create runnable jars using File -> Export -> Runnable JAR File -> Select launch configuration "flink-kafka-producer" and export destination as "C:\GenericNotificationEngine\RunnableJars\flink-kafka-producer.jar"
			
	11. Run the GenericNotificationEngine
		1. Before go for running the Flink job complete the above steps.....
		2. You can directly run the batch file or by running the runnable jar to start the flink job
 			a) Batch files (Make sure the runnable JAR files are kept in C:\GenericNotificationEngine\RunnableJars)
			  First we need to start the "Start-flink-kafka-consumer-emailAlert.bat"
			  Secondly we need to start "Start-flink-kafka-producer.bat"
			  
			b) or run as below (start consumer first and then producer)
			Go to the directory where runnable jars are present, for me it's C:\GenericNotificationEngine\RunnableJars
			Open command line
			Type this exccluding `"` - "start java -jar flink-kafka-consumer-emailAlert.jar --topic streamstest --bootstrap.servers localhost:9092 --zookeeper.connect localhost:2181 --group.id myGroup"
			Type this exccluding `"` - start java -jar flink-kafka-producer.jar --topic streamstest --bootstrap.servers localhost:9092
