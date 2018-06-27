FROM openjdk:8-jre

MAINTAINER yangwenbo yangwb@greenmarks.org

EXPOSE 9090

ENV TZ=Asia/Shanghai

RUN mkdir /usr/local/hsweb && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /usr/local/hsweb

#ENV JAR_FILE=target/HSWeb.jar
ARG JAR_FILE

ADD ${JAR_FILE} /usr/local/hsweb/hsweb.jar

CMD [ "java" ,  "-jar" , "hsweb.jar" ]
