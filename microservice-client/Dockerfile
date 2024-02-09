FROM maven:3.9.1 AS maven
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package

FROM openjdk:17-jdk-slim
WORKDIR /opt/app
COPY --from=maven /usr/src/app/target/microservice-client-0.0.1-SNAPSHOT.jar /opt/app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "microservice-client-0.0.1-SNAPSHOT.jar"]