FROM java:8-jre
MAINTAINER sacredrelict

VOLUME /tmp
ADD ./target/data-storage.jar /app/
CMD ["java", "-Xmx1g", "-jar", "/app/data-service.jar"]

EXPOSE 9191
