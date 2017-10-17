FROM java:8
VOLUME /tmp
ADD *.jar app.jar
ENV TZ=America/Sao_Paulo
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]