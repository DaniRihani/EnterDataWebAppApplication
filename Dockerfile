FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.12-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]