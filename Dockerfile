FROM openjdk:17
COPY target/blogging-api-0.0.1-SNAPSHOT.jar blogging-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/blogging-api-0.0.1-SNAPSHOT.jar"]