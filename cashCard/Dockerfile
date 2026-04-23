WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle bootJar --no-daemon

# --- Fase 2: Run ---
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

# Espone la porta standard di Spring Boot
EXPOSE 8080

# Comando di avvio
ENTRYPOINT ["java", "-jar", "app.jar"]