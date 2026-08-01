FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/employee-service-1.0.0.jar app.jar
ENV SERVER_PORT=8081
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
