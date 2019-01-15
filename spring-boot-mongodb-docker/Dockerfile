FROM openjdk:8-alpine
VOLUME /tmp
ADD target/spring-boot-mongodb-docker-0.0.1-SNAPSHOT.jar spring-boot-mongodb-docker.jar
EXPOSE 8080
RUN sh -c 'touch /spring-boot-mongodb-docker.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/test", "-Djava.security.egd=file:/dev/./urandom","-jar","/spring-boot-mongodb-docker.jar"]