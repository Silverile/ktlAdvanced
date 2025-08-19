# Use a Gradle image to build the project
FROM gradle:8.4.0-jdk17 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build --no-daemon

# Use a minimal JVM image to run the app
FROM eclipse-temurin:17-jre
COPY --from=build /app/build/libs/ktlAdvanced-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
