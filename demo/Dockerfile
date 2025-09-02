FROM openjdk:21-jdk
LABEL authors="nando2004rs@gmail.com"
WORKDIR /application
COPY target/demo-0.0.1-SNAPSHOT.jar projeto.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "projeto.jar"]