FROM openjdk:23
EXPOSE 8080
ADD ./target/task-manager-0.0.1-SNAPSHOT.jar task-manager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","task-manager-0.0.1-SNAPSHOT.jar"]