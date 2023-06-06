FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
RUN mvn install
COPY src ./src
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /api
COPY --from=build app/target/travel-expense-1.0.0.jar app.jar
CMD ["java", "-jar", "app.jar"]