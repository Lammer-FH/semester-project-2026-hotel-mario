# Build stage
FROM gradle:9.5.1-jdk25-corretto as builder

WORKDIR /app
COPY backend/ .

RUN gradle build -x test --no-daemon

# Runtime stage
FROM eclipse-temurin:25-jre-jammy

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
