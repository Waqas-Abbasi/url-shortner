FROM openjdk:12
FROM maven:latest

COPY . .

EXPOSE 8080

RUN mvn clean package

CMD ["mvn", "exec:java"]