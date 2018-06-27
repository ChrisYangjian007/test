#!/bin/sh

# 指定jdk目录&&AppName
export JAVA_HOME=/usr/java/java8
APP_NAME=hz-test.jar
echo $JAVA_HOME
echo $APP_NAME

#nohup命令后台启动jar包并且输出日志
nohup java -jar $APP_NAME >>logs/start.log 2>>logs/startError.log &

echo "Wait 15 seconds..........."
#sleep等待15秒后，判断包含AppName的线程是否存在
sleep 15

if test $(pgrep -f $APP_NAME|wc -l) -eq 0
then
   echo "Start Failed"
   exit 2
else
   echo "Start Successed"
   exit 0
fi
