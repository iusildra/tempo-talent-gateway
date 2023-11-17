FROM openjdk:17-alpine
LABEL maintainer="lucas.nouguier@protonmail.com"
VOLUME /main-app
ADD build/libs/gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "/app.jar"]