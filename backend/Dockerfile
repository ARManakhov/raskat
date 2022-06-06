FROM maven:3-openjdk-11 as build-stage
COPY ./ .
RUN mvn -B clean package -Dmaven.test.skip=true

FROM openjdk:11 as production-stage
COPY --from=build-stage target/*.jar /app.jar
ENTRYPOINT exec java -jar /app.jar
