package com.josegc789.trellobridge.bridge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Label {
    private String id;
    private String name;
    private String color;
}
