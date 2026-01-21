# Build stage
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Build arguments for environment
ARG DB_HOST=localhost
ARG DB_PORT=3306
ARG DB_NAME=receipts
ARG DB_USERNAME=root
ARG DB_PASSWORD=password
ARG SZAMLAZZ_API_KEY=your_api_key_here

# Copy source code
COPY src src

# Create .env from .env.example if it doesn't exist
COPY .env.example .env.template
RUN if [ ! -f ".env" ]; then cp .env.template .env; fi

# Replace placeholders in .env with build args
RUN sed -i "s|__DB_HOST__|${DB_HOST}|g" .env && \
    sed -i "s|__DB_PORT__|${DB_PORT}|g" .env && \
    sed -i "s|__DB_NAME__|${DB_NAME}|g" .env && \
    sed -i "s|__DB_USERNAME__|${DB_USERNAME}|g" .env && \
    sed -i "s|__DB_PASSWORD__|${DB_PASSWORD}|g" .env && \
    sed -i "s|__SZAMLAZZ_API_KEY__|${SZAMLAZZ_API_KEY}|g" .env

# Build the application
RUN ./mvnw clean package -DskipTests

# Run stage
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Install curl for healthcheck
RUN apk add --no-cache curl

# Copy the JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Copy the .env file from build stage
COPY --from=build /app/.env .env

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
