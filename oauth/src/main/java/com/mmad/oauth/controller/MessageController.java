package com.mmad.oauth.controller;

import com.mmad.oauth.config.Constant;
import com.mmad.oauth.util.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constant.MESSAGE_CONTEXT)
@RequiredArgsConstructor
public class MessageController {

    private MessageProducer messageProducer;

    @Autowired
    public void setKafkaProducer(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping(value = Constant.SEND_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> publish(@RequestParam String message) {
        messageProducer.sendMessage("oauth-topic", message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
