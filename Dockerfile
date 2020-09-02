FROM openjdk:14-buster

COPY consumer-0.0.1.jar .

CMD java -jar consumer-0.0.1.jar