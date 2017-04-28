FROM openjdk:alpine

COPY target/calc-rest-swarm.jar /opt/calc-rest/calc-rest-swarm.jar

CMD java -jar /opt/calc-rest/calc-rest-swarm.jar
