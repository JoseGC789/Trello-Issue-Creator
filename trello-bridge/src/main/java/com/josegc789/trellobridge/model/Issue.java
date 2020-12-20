package com.josegc789.trellobridge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class Issue extends Card {
    private final String description;

    @JsonCreator
    public Issue(@JsonProperty(value = "title", required = true) String title,
                 @JsonProperty(value = "description", required = true) String description) {
        super(title);
        this.description = description;
    }
}
