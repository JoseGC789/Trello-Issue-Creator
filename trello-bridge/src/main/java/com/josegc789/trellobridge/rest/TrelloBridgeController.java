package com.josegc789.trellobridge.rest;

import com.josegc789.trellobridge.bridge.TrelloProxy;
import com.josegc789.trellobridge.model.Card;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class TrelloBridgeController {

    private final TrelloProxy trello;

    public TrelloBridgeController(TrelloProxy trello) {
        this.trello = trello;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> bridge(@RequestBody Card card){
        return ResponseEntity.ok(trello.createCard());
    }

}
