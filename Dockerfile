FROM openjdk:17
COPY "./target/ride_hairing-0.0.1-SNAPSHOT.jar" "ride_hailing.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ride_hailing.jar"]