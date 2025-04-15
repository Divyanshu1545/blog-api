FROM alvistack/openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM alvistack/openjdk-17
COPY --from=build /target/blogging-api-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]