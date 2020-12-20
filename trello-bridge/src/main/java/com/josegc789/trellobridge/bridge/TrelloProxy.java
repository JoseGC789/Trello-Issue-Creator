package com.josegc789.trellobridge.bridge;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class TrelloProxy {
    @Value("${trello.board.list.toDo}")
    private String todo;
    @Value("${trello.security.token}")
    private String token;
    @Value("${trello.security.key}")
    private String key;

    public Map<String, String> createCard(){
        Map<String, String> data = new HashMap<>();
        data.put("todo", todo);
        data.put("token", token);
        data.put("key", key);
        return data;
    }
}
