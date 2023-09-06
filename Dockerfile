#Docker file

#jdk8 Image Start
FROM openjdk:1.8-jdk
#인자 정리 - Jar
ARG JAR_FILE=build/libs/*.jar
#jar File Copy
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]