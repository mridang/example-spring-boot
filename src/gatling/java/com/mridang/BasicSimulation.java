package com.mridang;

import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import java.util.Random;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

@SuppressWarnings({"unused", "CanBeFinal"})
public class BasicSimulation extends Simulation {

    private static final Random RANDOM = new Random();

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://mridang.dev.nos.to:9000")
            .disableCaching()
            .disableWarmUp();

    ScenarioBuilder scn = scenario("Scenario Name")
            .exec(http("request_1")
                    .get(session -> "/?" + session.scenario()));

    {
        setUp(scn.injectClosed(constantConcurrentUsers(500).during(60)).protocols(httpProtocol));
    }
}
