FROM maven:latest AS build
WORKDIR /app
ENV DB_HOST=db
ENV DB_PORT=3306
COPY pom.xml .
RUN mvn install
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build app/target/travel-expense-1.0.0.jar app.jar
CMD ["java", "-jar", "app.jar"]
