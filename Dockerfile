FROM openjdk:11
COPY target/organizer-api-0.0.1-SNAPSHOT.jar organizer-api.jar
ENTRYPOINT ["java","-jar","/organizer-api.jar"]