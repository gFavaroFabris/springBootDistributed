FROM gradle:8.14.0-jdk21 AS build
WORKDIR /workspace

COPY . ./

RUN gradle :camel:bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /workspace/camel/build/libs/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
