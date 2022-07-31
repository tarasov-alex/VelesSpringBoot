FROM openjdk:11-jre-slim
EXPOSE 8080
ADD target/velesBase-0.0.1-SNAPSHOT.jar velesBase.jar

ENTRYPOINT ["java", "-jar", "/velesBase.jar"]