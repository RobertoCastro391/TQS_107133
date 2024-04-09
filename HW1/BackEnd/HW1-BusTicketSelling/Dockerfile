FROM openjdk:17 AS builder
WORKDIR /hw1
COPY . .
RUN mvn package -DskipTests
FROM openjdk:17
WORKDIR /app
ARG JAR_FILE=target/HW1-BusTicketSelling-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
