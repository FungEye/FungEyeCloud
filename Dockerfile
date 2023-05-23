FROM eclipse-temurin:17-jdk-alpine

RUN apk add --no-cache tzdata
ENV TZ=Europe/Copenhagen
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw
RUN ./mvnw dependency:resolve

COPY src ./src
CMD ["./mvnw", "spring-boot:run"]
