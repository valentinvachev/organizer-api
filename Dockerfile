FROM openjdk:11
COPY target/organizer-api-*.jar organizer-api.jar
ENTRYPOINT ["java","-jar","/organizer-api.jar"]