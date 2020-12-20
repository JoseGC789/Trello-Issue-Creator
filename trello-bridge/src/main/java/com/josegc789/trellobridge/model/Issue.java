package com.josegc789.trellobridge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Issue extends Card {
    private final String description;

    @JsonCreator
    public Issue(@JsonProperty(value = "title", required = true) String title,
                 @JsonProperty(value = "description", required = true) String description) {
        super(title);
        this.description = description;
    }

    @Override
    public Map<String, String> toPayload() {
        Map<String, String> payload = new HashMap<>();
        payload.put("name", getTitle());
        payload.put("desc", description);
        payload.put("label", "Issue");
        return payload;
    }
}
