FROM openjdk:21-slim-buster
ADD /target/elhmel-0.0.1-SNAPSHOT.jar elhmel.jar
ENTRYPOINT ["java","-jar","/elhmel.jar"]
EXPOSE 8084