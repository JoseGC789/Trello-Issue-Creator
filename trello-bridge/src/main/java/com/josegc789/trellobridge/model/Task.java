package com.josegc789.trellobridge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class Task extends Card {
    private final Categories category;

    @JsonCreator
    public Task(@JsonProperty(value = "title", required = true) String title,
                @JsonProperty(value = "category", required = true) Categories category) {
        super(title);
        this.category = category;
    }

    private enum Categories {
        MAINTENANCE("maintenance"),
        RESEARCH("research"),
        TEST("test");

        private final String value;

        Categories(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }
}
