FROM maven:3.6-jdk-8 AS build
COPY trello-bridge/src /home/app/trello-bridge/src
COPY trello-bridge/pom.xml /home/app/trello-bridge
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:8
COPY --from=build /home/app/trello-bridge/target/trello-bridge-1.0-SNAPSHOT.jar /usr/local/lib/trello-bridge.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","/usr/local/lib/trello-bridge.jar"]