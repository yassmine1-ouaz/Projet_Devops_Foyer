FROM openjdk:17-slim
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} tp-foyer-5.0.0.jar
ENTRYPOINT ["java", "-jar" ,"/tp-foyer-5.0.0.jar"]
EXPOSE 8089