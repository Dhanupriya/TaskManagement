FROM openjdk:8-jdk

COPY config.yml.yml /data/task-management/config.yml
COPY /target/TaskManagement-1.0-SNAPSHOT.jar/data/task-management/TaskManagement-1.0-SNAPSHOT.jar

WORKDIR /data/task-management

RUN java -version

CMD ["java","-jar","TaskManagement-1.0-SNAPSHOT.jar","server","config.yml"]

EXPOSE 8080-8081