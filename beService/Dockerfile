FROM maven:3.9.16-eclipse-temurin-25 AS build
WORKDIR /workspace

COPY pom.xml .
COPY beService/pom.xml beService/pom.xml
COPY beService/src beService/src
RUN mvn -B -f beService/pom.xml -DskipTests package

FROM eclipse-temurin:25-jre
WORKDIR /app

COPY --from=build /workspace/beService/target/*.jar /app/app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
