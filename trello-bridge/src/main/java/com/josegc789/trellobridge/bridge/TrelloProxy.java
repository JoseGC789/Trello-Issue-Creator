package com.josegc789.trellobridge.bridge;

import com.josegc789.trellobridge.ResourceNotFound;
import com.josegc789.trellobridge.model.Card;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static com.josegc789.trellobridge.util.Misc.RANDOM;

@Service
public class TrelloProxy {
    private final RestOperations rest;
    private final Map<String, String> labels;
    private final List<String> members;
    private final String toDoId;
    @Value("${trello.security.token}")
    private String token;
    @Value("${trello.security.key}")
    private String key;

    public TrelloProxy(RestOperations rest,
                       @Qualifier("labels") Map<String, String> labels,
                       @Qualifier("members") List<String> members,
                       @Qualifier("list") String toDoId) {
        this.rest = rest;
        this.labels = labels;
        this.members = members;
        this.toDoId = toDoId;
    }

    public NewCard createCard(Card card){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.trello.com/1/cards")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("idList", toDoId);
        Map<String, String> params = card.toPayload();
        for (String key: params.keySet()) {
            String value = params.get(key);
            builder.queryParam(key, value);
            if ("label".equals(key)){
                String label = labels.get(value);
                builder.queryParam("idLabels", Collections.singletonList(existsOrThrow(label, key)));
                if ("Bug".equals(value)){
                    String member = any();
                    builder.queryParam("idMembers", existsOrThrow(member, key));
                }
            }
        }
        return rest.postForObject(builder.toUriString(), null, NewCard.class);
    }

    private <T> T existsOrThrow(T value, String key){
        if (null == value){
            throw new ResourceNotFound(key + " not found. Please contact an administrator!");
        }
        return value;
    }

    private String any() {
        return members.get(RANDOM.nextInt(members.size()));
    }
}
