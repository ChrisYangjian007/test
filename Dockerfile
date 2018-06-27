FROM openjdk:8-jre
MAINTAINER yangwenbo
WORKDIR /usr/local
ADD apache-tomcat-8.5.27.tar.gz
ADD solr-7.1.0.tgz
ENV TOMCAT_HOME="/usr/local/solr/apache-tomcat-8.5.24" \
    SOLR_TGZ="/usr/local/solr-7.2.0"
RUN apt-get update && \
  apt-get -y install vi && \
  rm -rf /var/lib/apt/lists/* && \
  mkdir -p /usr/local/solr && \
  tar -zxvf apache-tomcat-8.5.27.tar.gz -C /usr/local/solr/ && \
  mkdir -p /usr/local/solr/home && \
  mkdir -p /usr/local/solr/logs && \
  tar -zxvf solr-7.1.0.tgz && \
  mkdir -p /$TOMCAT_HOME/webapps/solr && \
  cp -rf $SOLR_TGZ/server/solr-webapp/webapp/* $TOMCAT_HOME/webapps/solr && \
  cp $SOLR_TGZ/server/lib/ext/*.jar $TOMCAT_HOME/webapps/solr/WEB-INF/lib && \
  cp $SOLR_TGZ/server/lib/gmetric4j-1.0.7.jar $TOMCAT_HOME/webapps/solr/WEB-INF/lib/ && \
  cp $SOLR_TGZ/server/lib/metrics-*.jar $TOMCAT_HOME/webapps/solr/WEB-INF/lib/ && \
  mkdir -p $TOMCAT_HOME/webapps/solr/WEB-INF/classes && \
  cp $SOLR_TGZ/server/resources/log4j.properties $TOMCAT_HOME/webapps/solr/WEB-INF/classes && \
  cp $SOLR_TGZ/server/solr/* -rf /usr/local/solr/home && \
  rm -rf $SOLR_TGZ
ENTRYPOINT [ "sh" ,  "-c" , "$TOMCAT_HOME/bin/startup.sh && tail -f /dev/null" ]