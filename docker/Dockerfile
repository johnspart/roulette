FROM adoptopenjdk/openjdk11:alpine

ENV REDIS_PORT "6379"
ENV REDIS_HOST "127.0.0.1"

ENV LANG C.UTF-8
ENV APP_TIMEZONE UTC

WORKDIR /usr/src/app

ADD ./roulette-0.0.1-SNAPSHOT.war .

ENTRYPOINT ["java", "-jar", "roulette-0.0.1-SNAPSHOT.war"]