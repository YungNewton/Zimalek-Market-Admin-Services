FROM openjdk:17-alpine

# install bash
RUN \
  apk add --no-cache bash

RUN mkdir /uploads
# install java

EXPOSE 3002

ADD target/market-admin-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar