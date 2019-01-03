#konfigurasi Dockerfile
FROM frolvlad/alpine-oraclejdk8:slim
LABEL Description="Demo Jpa Eureka" Version="0.0.1"
VOLUME /tmp
ADD target/demo-jpa-eureka.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]