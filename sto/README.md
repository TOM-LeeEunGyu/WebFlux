# IBK STO POC 백엔드 서버 설치 매뉴얼

## 환경 설정
- Kotlin 1.8.21 on Java 17
- Spring Boot 3.1.1
- build: gradle v8.1.1

코틀린과 스프링 부트 3대 버전으로 구성된 프로젝트이다.

따라서 자바는 최소 17또는 그 이상의 버전이 설치되어야 한다.

## Jenkins Deploy

젠킨스를 이용한 배포를 하기 위해서는 젠킨스를 설치해야 한다.

일반적으로 젠킨스를 통해 배포하는 경우 일반적으로 `git`을 통해 배포하게 된다.

프라이빗하게 운영할 경우 `gitlab`을 따로 설치해서 운영할 수 있다.

또는 `github`에 `private repository`로 설정해서 내부에서만 소스를 관리할 수 있도록 설정을 하고 이를 통해 젠킨스를 통해 배포가 가능하다.

단 이 경우에는 `git`을 운영해야 한다.

따라서 이 방법은 소스를 인수받는 팀에서 자체적으로 처리할 수 있다. 

이 방법은 여기서 논외로 한다.

## Direct Source Build And Deploy

자바를 설치했다면 이 후에 `JAVA_HOME`설정을 해야 한다.

다음 해당 `OS`에 따라 설정하면 된다.

### MacOS

`MacOs`의 경우에는 다음과 `Java 17 버전`을 기준으로 한다.

그 이상의 버전의 경우에는 설치시 경로가 다를 수 있기 때문에 이 부분은 자바가 설치된 경로를 확인하는 것이 좋다.

```
export JAVA_HOME=/Users/{your mac name}/Library/Java/JavaVirtualMachines/corretto-17.0.5/Contents/Home
```
여기서 `{your mac name}`부분은 개인마다 다르기 때문에 개인에 따라 설정된 경로를 확인하면 된다.

### Window OS

윈도우의 경우에는 다음 링크를 참조하자.

[Window OS JAVA 설치 및 환경변수 설정](https://velog.io/@soyul2823/window-JAVA-%EC%84%A4%EC%B9%98-%EB%B0%8F-%ED%99%98%EA%B2%BD%EB%B3%80%EC%88%98-%EC%84%A4%EC%A0%95)

## 배포를 위한 jar 파일 생성하기

제공된 소스 파일을 압축 해제한다.

현재 해당 소스 프로젝트명은 `ibk-sto-poc-backend`로 `터미널 (Window의 경우에는 CMD)`을 통해서 해당 루트 폴더로 진입한다.

```
$> cd ibk-sto-poc-backend
ibk-sto-poc-backend$> ./gradlew clean
# clean Task

ibk-sto-poc-backend$> ./gradlew bootJar  
# build bootJar
```
해당 태스크가 완료가 되면 폴더 내에 `build`폴더가 생성된다.

`build > libs`폴더로 진입하면 `ibk-sto-poc-0.0.1-SNAPSHOT.jar`파일이 생성된 것을 확인할 수 있다.

### SFTP

생성된 해당 파일을 설치할 서버로 파일을 이동시킨다.

현재 제공된 소스 폴더를 보면 `src/main/resources/scripts`경로에 3개의 파일이 존재한다.

이 3개의 파일도 서버의 특정 폴더로 이동시켜야 한다.

이때 3개의 파일은 서버의 상황에 따라서 수정을 해야 한다.

#### logback-dev.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="5 seconds">

    <include resource="org/springframework/boot/logging/logback/defaults.xml"/>
    <conversionRule conversionWord="clr" converterClass="org.springframework.boot.logging.logback.ColorConverter"/>
    <conversionRule conversionWord="wex" converterClass="org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter"/>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <Pattern>[%d{YYYY-MM-dd HH:mm:ss z, Asia/Seoul}] [%-5level] [%thread] %logger{36}:%L - %msg -- %X{log.remoteAddr}%n</Pattern>
        </encoder>
    </appender>

    <property name="LOG_DIR" value="/home/ubuntu/ibk-poc/logs"/>
    <property name="FILE_LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} %5p [%t] %C:%L - %msg%n%wex"/>

    <appender name="dailyRollingFileAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_DIR}/ibk-poc.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_DIR}/ibk-poc.%d{yyyy-MM-dd}.log.%i.gz</fileNamePattern>
            <maxHistory>30</maxHistory>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>100MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <logger name="io.medium.poc" additivity="false" level="INFO">
        <appender-ref ref="dailyRollingFileAppender"/>
        <appender-ref ref="CONSOLE" />
    </logger>

    <logger name="org.springframework" additivity="false" level="INFO">
        <appender-ref ref="dailyRollingFileAppender"/>
        <appender-ref ref="CONSOLE" />
    </logger>

    <root level="INFO">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="dailyRollingFileAppender"/>
    </root>

</configuration>
```
이 파일에서 다음 부분을 수정해야 한다.

```xml
<!--해당 부분을 아래에 맞춰서 변경하도록 한다.-->
<!--<property name="LOG_DIR" value="/home/ubuntu/ibk-poc/logs"/>-->
<property name="LOG_DIR" value="{로그 파일이 저장될 전체 경로}"/>
```

#### stop.sh

```shell
#!/bin/sh

echo "IBK STO POC Backend Server stop...."

fuser -k /home/ubuntu/ibk-poc/ibk-sto-poc-0.0.1-SNAPSHOT.jar
```

여기서는 아래처럼 `ibk-sto-poc-0.0.1-SNAPSHOT.jar`파일이 위치하는 전체 경로를 변경해 줘야 한다.

```shell
#!/bin/sh

echo "IBK STO POC Backend Server stop...."

fuser -k /{jar 파일이 위치한 전체 경로}/ibk-sto-poc-0.0.1-SNAPSHOT.jar
```

#### restart.sh

```shell
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
```
몇 가지 수정해야 하는 부분이 있다.

현재는 서버 포트가 `8088`로 설정되어 있지만 만일 다른 포트로 서버를 띄우고자 한다면 이 부분의 값을 변경해 줘야 한다.

또한 `ibk-sto-poc-0.0.1-SNAPSHOT.jar`파일이 위치한 경로도 바꿔줘야 한다.

그리고 로그 파일 역시 마찬가지로 `logback-dev.xml`이 위치한 전체 경로로 변경해 주면 된다.

## 서버 시작 및 종료

설정이 완료가 되면 다음과 같이 쉘 스크립트를 실행하면 된다.

```
$> ./restart.sh
```
이 커맨드 라인을 실행하게 되면 서버가 이미 실행중이면 서버를 멈춘 이 후 다시 실행한다.

만일 서버를 멈추고 싶으면 다음 쉘 스크립트를 실행하면 된다.

```
$> ./stop.sh
```


# API 인터페이스 명세서

로컬에서 서버를 실행하게 되면 다음 주소로 접속해 스웨거 문서를 확인할 수 있다.

[http://localhost:8088/ibk-sto-poc](http://localhost:8088/ibk-sto-poc)

만일 해당 백엔드 API 서버가 설치 될 아이피 또는 도메인이 존재한다면 다음과 같은 형식으로 변경하면 된다.

```
http://{서버 아이피}:{설정 포트 번호}/ibk-sto-poc

또는

http://{domain}/ibk-sto-poc
```