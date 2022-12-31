FROM openjdk:17

#RUN apt-get update && \
#    apt-get install tzdata
#
#ENV TZ Asia/Seoul

ADD app.jar app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=local", "/app.jar"]
