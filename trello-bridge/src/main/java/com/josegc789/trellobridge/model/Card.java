package com.josegc789.trellobridge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Issue.class, name = "issue"),
        @JsonSubTypes.Type(value = Bug.class, name = "bug"),
        @JsonSubTypes.Type(value = Task.class, name = "task")
})
@Getter
public abstract class Card {

    private final String title;

    @JsonCreator
    protected Card(String title) {
        this.title = title;
    }
}
