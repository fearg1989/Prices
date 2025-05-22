FROM openjdk:23-slim
WORKDIR /app
COPY target/Prices-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]