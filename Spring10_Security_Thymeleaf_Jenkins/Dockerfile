FROM openjdk:17-ea-11-jdk-slim

COPY ./target/myhome4-0.0.1-SNAPSHOT.jar /security.jar 
COPY ./icecream.png  ./icecream.png 

RUN mkdir ./image && mv ./icecream.png ./image/icecream.png

ENTRYPOINT ["java", "-jar", "security.jar"]
