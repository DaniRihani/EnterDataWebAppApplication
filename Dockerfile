# First stage - Build the Java app
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

# Install Maven
RUN apt update && apt install -y maven

# Copy source code
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Second stage - Create a minimal runtime image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
