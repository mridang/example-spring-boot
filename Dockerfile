# syntax = docker/dockerfile:1.2
FROM amazoncorretto:17.0.2 AS builder

MAINTAINER Mridang Agarwalla "mridang.agarwalla@gmail.com"

RUN yum -y install hostname

COPY . /tmp/springy/
WORKDIR /tmp/springy
RUN ./gradlew installDist --no-daemon

FROM builder AS production

WORKDIR /srv/mridang
COPY --from=builder /tmp/springy/build/install/springy .
#RUN chown -R java:java /srv/springy
#USER java

HEALTHCHECK --start-period=30s --interval=30s --timeout=3s --retries=3 CMD curl --fail http://localhost:9000/actuator/health || exit 1
EXPOSE 9000 7091
ENTRYPOINT ["./init-and-run.sh"]
