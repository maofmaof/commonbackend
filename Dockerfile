FROM openjdk:17.0.1-jdk-slim
COPY target/*.jar /home/app/commonbackend.jar
ENTRYPOINT ["java", "-jar", "/home/app/commonbackend.jar"]