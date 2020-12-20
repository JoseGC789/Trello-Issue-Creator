package com.josegc789.trellobridge.config;

import com.josegc789.trellobridge.bridge.model.Label;
import com.josegc789.trellobridge.bridge.model.List;
import com.josegc789.trellobridge.bridge.model.Member;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@Configuration
@Primary
public class CucumberConfig {

    @Bean
    @Profile("test")
    public RestOperations mocked(){
        List[] lists = new List[]{new List("To Do","To Do")};
        Member[] members = new Member[]{new Member("Member1")};
        Label[] labels = new Label[]
                {new Label("Bug1","Bug","red"),
                        new Label("Issue1","Issue","red"),
                        new Label("Maintenance1","Maintenance","red"),
                        new Label("Research1","Research","red"),
                        new Label("Test1","Test","red")};
        RestOperations mocked = Mockito.mock(RestTemplate.class);
        when(mocked.getForObject(any(String.class), eq(Member[].class)))
                .thenReturn(members);
        when(mocked.getForObject(any(String.class), eq(List[].class)))
                .thenReturn(lists);
        when(mocked.getForObject(any(String.class), eq(Label[].class)))
                .thenReturn(labels);
        return mocked;
    }
}
