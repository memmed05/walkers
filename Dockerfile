FROM openjdk:17-jdk-slim AS build

COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle
RUN ./gradlew dependencies

COPY src src
RUN ./gradlew build

FROM openjdk:17-jdk-slim

WORKDIR /open-weather

COPY --from=build /build/libs/*.jar walkers-app.jar

ENTRYPOINT ["java", "-jar", "walkers-app.jar"]