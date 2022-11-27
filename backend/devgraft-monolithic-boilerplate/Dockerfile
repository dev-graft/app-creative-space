FROM openjdk:11

RUN apt-get update && \
    apt-get install --no-cache tzdata
    
ENV TZ Asia/Seoul

ADD app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
