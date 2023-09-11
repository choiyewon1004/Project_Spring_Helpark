FROM openjdk:8-jdk
WORKDIR /portfolio
COPY portfolio-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]