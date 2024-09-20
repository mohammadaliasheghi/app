package com.mmad.springai.controller;

import com.mmad.springai.service.ChatGenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class GenAIChatController {

    private final ChatModel chatModel;

    @GetMapping("/chat-gen")
    public String chatModelGen(@RequestBody ChatGenRequest chatGenRequest) {
        System.out.println("ChatModel - STARTED!!! - " + chatGenRequest.prompt());
        String response = chatModel.call(chatGenRequest.prompt());
        System.out.println("ChatModel - DONE!!! " + response);
        return response;
    }
}
