package com.josegc789.trellobridge.config;

import com.josegc789.trellobridge.bridge.model.Label;
import com.josegc789.trellobridge.bridge.model.List;
import com.josegc789.trellobridge.bridge.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class AppConfig {

    @Value("${trello.board}")
    private String board;
    @Value("${trello.security.token}")
    private String token;
    @Value("${trello.security.key}")
    private String key;

    @Bean
    @Profile("live")
    public RestOperations template(){
        return new RestTemplateBuilder().build();
    }

    @Bean("members")
    public java.util.List<String> members(RestOperations operations){
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.trello.com/1/boards/" + board + "/memberships")
                    .queryParam("key", key)
                    .queryParam("token", token);
            Member[] payload = operations.getForObject(builder.toUriString(), Member[].class);
            return Arrays.stream(Objects.requireNonNull(payload)).map(Member::getIdMember).collect(Collectors.toList());
        }catch (Exception e){
            log.error("Fatal error while reading members in board!", e);
            throw e;
        }
    }

    @Bean("labels")
    public Map<String, String> labels(RestOperations operations){
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.trello.com/1/boards/" + board + "/labels")
                    .queryParam("key", key)
                    .queryParam("token", token);
            Label[] payload = operations.getForObject(builder.toUriString(), Label[].class);
            Map<String, String> labels = new HashMap<>();
            for (Label label: Objects.requireNonNull(payload)) {
                labels.put(label.getName(), label.getId());
            }
            return Collections.unmodifiableMap(labels);
        } catch (Exception e){
            log.error("Fatal error while reading labels in board!", e);
            throw e;
        }
    }

    @Bean("list")
    public String lists(RestOperations operations){
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.trello.com/1/boards/" + board + "/lists")
                    .queryParam("key", key)
                    .queryParam("token", token);
            List[] payload = operations.getForObject(builder.toUriString(), List[].class);
            for (List list: Objects.requireNonNull(payload)) {
                if("To Do".equals(list.getName())){

                    return list.getId();
                }
            }
            return "";
        } catch (Exception e){
            log.error("Fatal error while reading lists in board!", e);
            throw e;
        }
    }
}
