FROM openjdk:8-jre

MAINTAINER yangwenbo yangwb@greenmarks.org

EXPOSE 8080

ENV TZ=Asia/Shanghai

RUN mkdir /usr/local/hsapi && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /usr/local/hsapi

#ENV JAR_FILE=target/API.jar
ARG JAR_FILE

ADD ${JAR_FILE} /usr/local/hsapi/hsapi.jar

CMD [ "java" ,  "-jar" , "hsapi.jar" ]
