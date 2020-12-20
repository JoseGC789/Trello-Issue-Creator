package com.josegc789.trellobridge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class Bug extends Card {
    private final String description;

    @JsonCreator
    public Bug(@JsonProperty(value = "description", required = true) String description) {
        super("bug-ALPHABRAVOCHARLIE-123456");
        this.description = description;
    }
}
