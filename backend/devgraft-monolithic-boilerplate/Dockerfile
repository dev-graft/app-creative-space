FROM openjdk:11

RUN apk update && \
    apk add --no-cache tzdata
    
ENV TZ Asia/Seoul

ADD app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
