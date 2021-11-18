FROM openjdk:15-jdk-alpine
RUN addgroup -S contractors && adduser -S contractors -G contractors
WORKDIR /contractors/
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java","-jar","app.war"]