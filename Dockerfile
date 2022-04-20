FROM openjdk:8-jdk-alpine
COPY demo/target/*.jar demo-1.0.0.jar
ENTRYPOINT ["java","-jar","/demo-1.0.0.jar"]