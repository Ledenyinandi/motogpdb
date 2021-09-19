FROM adoptopenjdk/openjdk11:jre-11.0.2.9-alpine
ADD target/motogpdb-0.0.1-SNAPSHOT.jar motogpdb-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "motogpdb-0.0.1-SNAPSHOT.jar"]