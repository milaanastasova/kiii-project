FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/music-store-0.0.1-SNAPSHOT.jar /app/music-store.jar

EXPOSE 8080

CMD ["java", "-jar", "music-store.jar"]
