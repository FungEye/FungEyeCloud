FROM maven:3.9.2-eclipse-temurin-17-alpine as BUILD
RUN mkdir -p /app
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn -B package --file pom.xml -DskipTests

FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
EXPOSE 443
COPY --from=build /app/target/*jar cloud-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/cloud-0.0.1-SNAPSHOT.jar"]