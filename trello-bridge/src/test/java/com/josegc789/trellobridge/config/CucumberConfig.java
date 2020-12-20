package com.josegc789.trellobridge.config;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestOperations;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Primary
public class CucumberConfig {

    @MockBean
    protected RestOperations mocked;

    @Bean("members")
    public List<String> members(){
        return Collections.singletonList("Member1");
    }

    @Bean("labels")
    public Map<String, String> labels(){
        Map<String, String> labels = new HashMap<>();
        labels.put("Bug","Bug1");
        labels.put("Issue","Issue1");
        labels.put("Maintenance","Maintenance1");
        labels.put("Research","Research1");
        labels.put("Test","Test1");
        return labels;
    }

    @Bean("list")
    public String lists(){
        return "To Do";
    }
}
