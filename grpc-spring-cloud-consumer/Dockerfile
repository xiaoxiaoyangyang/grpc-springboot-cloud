FROM registry.sensetime.com/smart-casino/cicd/maven:3.6.0-jdk-8-irdc as build-stage
WORKDIR /app
COPY . .
RUN mvn clean install -Dmaven.test.skip=true

FROM registry.sensetime.com/smart-casino/cicd/openjdk:8-jdk-alpine as production-stage
#ARG CI_PROJECT_NAME
COPY --from=build-stage /app/target/8783.jar /rws3/8783/app.jar
WORKDIR /rws3/8783
ENV JAVA_OPTS="-XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=1024m -Xms1024m -Xmx2048m -Xmn512m -Xss512k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dlog4j2.formatMsgNoLookups=true"
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar app.jar
EXPOSE 40301
