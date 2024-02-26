FROM alpine:3.14.2

RUN apk add openjdk11
COPY build/libs/pet-project.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]