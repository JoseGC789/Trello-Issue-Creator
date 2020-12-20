package com.josegc789.trellobridge.bridge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCard {
    private String id;
    private String shortUrl;
    private String url;
}
