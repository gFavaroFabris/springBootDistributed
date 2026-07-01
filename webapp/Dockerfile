FROM gradle:8.14.0-jdk21 AS build
WORKDIR /workspace

COPY . ./

RUN gradle :webapp:bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /workspace/webapp/build/libs/*.jar /app/app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
