package com.josegc789.trellobridge.features;

import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@DirtiesContext
public class ContextLoaderStep {

    @Given("the App is running")
    public void theServiceIsRunningAndAbleToReceiveAndProcessRequests() {
        // Dummy method so cucumber will recognize this class as glue
        // and use its context configuration.
    }
}