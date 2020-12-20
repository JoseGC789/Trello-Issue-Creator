package com.josegc789.trellobridge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.josegc789.trellobridge.util.Misc;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
@JsonIgnoreProperties("title")
public class Bug extends Card {
    private final String description;
    private static final String[] WORDS = {"Alpha","Bravo","Charlie","Delta","Echo","Foxtrot","Golf","Hotel","India"};

    @JsonCreator
    public Bug(@JsonProperty(value = "description", required = true) String description) {
        super(buildTitle());
        this.description = description;
    }

    private static String buildTitle() {
        StringBuilder words = new StringBuilder();
        for (int i = 0; i < 5; i++){
            words.append(WORDS[Misc.RANDOM.nextInt(WORDS.length)]);
        }
        return "bug-" + words.toString() + "-" + Misc.RANDOM.nextInt(10000);
    }

    @Override
    public Map<String, String> toPayload() {
        Map<String, String> payload = new HashMap<>();
        payload.put("name", getTitle());
        payload.put("desc", description);
        payload.put("label", "Bug");
        return payload;
    }
}
