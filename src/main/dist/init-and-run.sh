#!/bin/bash

if [ -z "$LAUNCH_TYPE" ]; then
    export LAUNCH_TYPE=$1
fi

if [ -z "$ENVIRONMENT_NAME" ]; then
    export ENVIRONMENT_NAME=$2
fi

if [[ $LAUNCH_TYPE != "local" ]]
then
  # shellcheck disable=SC2155
  export HOST=$(curl -s 169.254.169.254/latest/meta-data/local-hostname)
  # shellcheck disable=SC2155
  export LOCAL_IP=$(curl -s 169.254.169.254/latest/meta-data/local-ipv4)
else
  # shellcheck disable=SC2155
  export HOST=$(hostname)
  # shellcheck disable=SC2155
  export LOCAL_IP=$(hostname --ip-address)
fi

export RUN_OPTS="${RUN_OPTS} -XX:+PreserveFramePointer"
export RUN_OPTS="${RUN_OPTS} -XX:+ExitOnOutOfMemoryError"
export RUN_OPTS="${RUN_OPTS} -XX:-OmitStackTraceInFastThrow"
export RUN_OPTS="${RUN_OPTS} -Dcom.sun.management.jmxremote=true"
export RUN_OPTS="${RUN_OPTS} -Dcom.sun.management.jmxremote.port=7091"
export RUN_OPTS="${RUN_OPTS} -Dcom.sun.management.jmxremote.rmi.port=7091"
export RUN_OPTS="${RUN_OPTS} -Dcom.sun.management.jmxremote.ssl=false"
export RUN_OPTS="${RUN_OPTS} -Dcom.sun.management.jmxremote.authenticate=false"
export RUN_OPTS="${RUN_OPTS} -Dlaunch.type=\"${LAUNCH_TYPE}\""
export RUN_OPTS="${RUN_OPTS} -Dspring.profiles.active=\"${ENVIRONMENT_NAME}\""
export RUN_OPTS="${RUN_OPTS} -Djava.rmi.server.hostname=\"${LOCAL_IP}\""
export RUN_OPTS="${RUN_OPTS} -Dlog4j.appender.graylog2.originHost=\"${HOST}\""
export RUN_OPTS="${RUN_OPTS} -Dnewrelic.config.file=newrelic.yml"
export RUN_OPTS="${RUN_OPTS} -Dnewrelic.config.process_host.display_name=\"${HOST}\""
export RUN_OPTS="${RUN_OPTS} -Dnewrelic.environment=\"${ENVIRONMENT_NAME}\""
export RUN_OPTS="${RUN_OPTS} -javaagent:lib/newrelic-agent-7.6.0.jar"

./bin/run
