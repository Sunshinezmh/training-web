FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD integral-training-provider/target/training-web.jar training.jar
RUN sh -c 'touch /training.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /training.jar" ]