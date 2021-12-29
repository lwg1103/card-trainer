FROM amazoncorretto:11-alpine-jdk
MAINTAINER dc
COPY target/trainer-0.0.1-SNAPSHOT.war trainer-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/trainer-0.0.1-SNAPSHOT.war"]
