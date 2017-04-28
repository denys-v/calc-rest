Requirements
------------

- JDK 1.8
- Maven 3.3+


Running
-------

In the project directory invoke either:

mvn wildfly-swarm:run

or:

mvn package
java -jar target/calc-rest-swarm.jar


Testing
-------
curl http://localhost:8080/calc/add/1/2/3
curl http://localhost:8080/calc/subtract/100/23
curl http://localhost:8080/calc/multiply/2.5/2/2
curl http://localhost:8080/calc/divide/9/-3
