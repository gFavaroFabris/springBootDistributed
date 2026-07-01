FROM gradle:8.14.0-jdk21 AS build
WORKDIR /workspace

COPY . ./

RUN gradle :beService:bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /workspace/beService/build/libs/*.jar /app/app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
