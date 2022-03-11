FROM amazoncorretto:11-alpine
VOLUME /tmp
COPY build/libs/*.jar app.jar

HEALTHCHECK CMD curl --fail http://localhost/actuator/health || exit 1
ENTRYPOINT ["java","-jar","/app.jar"]

