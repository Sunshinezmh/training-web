FROM frolvlad/alpine-oraclejdk8:slim 
ENV JAVA_OPTS="-Xmx512m"
VOLUME /tmp 
ADD integral-trainingProject-web.jar app.jar 
#RUN bash -c 'touch /app.jar' 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"] 
EXPOSE 8761