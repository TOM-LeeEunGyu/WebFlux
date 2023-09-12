#!/bin/sh

PORT=$1

if [ -z $PORT ]
then
    PORT=8088
else
    PORT=$1
fi

echo "IBK STO POC Backend Server restart..."

JAR_PID=$(pgrep -f /home/ubuntu/ibk-poc/ibk-sto-poc-0.0.1-SNAPSHOT.jar)
JAR_PATH="/home/ubuntu/ibk-poc/ibk-sto-poc-0.0.1-SNAPSHOT.jar"

if [ -z "$JAR_PID" ]; then
    echo "IBK STO POC Backend Server is shutdown"
    echo "so, start IBK STO POC Backend Server Server"
    nohup java -Dspring.profiles.active=dev -Dlogging.config=/home/ubuntu/ibk-poc/config/logback-dev.xml -Dserver.port=$PORT -jar $JAR_PATH  >> /home/ubuntu/ibk-poc/logs/ibk-poc.log &
else
    echo "IBK STO POC Backend Server is running"
    echo "so, IBK STO POC Backend Server will be shutdown..."
   
    kill -9 $JAR_PID

    echo "now! restart IBK STO POC Backend Server"
    nohup java -Dspring.profiles.active=dev -Dlogging.config=/home/ubuntu/ibk-poc/config/logback-dev.xml -Dserver.port=$PORT -jar $JAR_PATH  >> /home/ubuntu/ibk-poc/logs/ibk-poc.log &

fi