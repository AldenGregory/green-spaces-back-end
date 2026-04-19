FROM eclipse-temurin:21-jre

COPY ./target/greenspaces.jar greenspaces.jar

EXPOSE 8080

CMD ["java", "-jar", "/greenspaces.jar"]