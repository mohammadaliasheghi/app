package com.mmad.springai.controller;

import com.mmad.springai.service.ImageGenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class GenAIImageModelController {

    private final ImageModel imageModel;

    @GetMapping("/image-gen")
    public String imageGen(@RequestBody ImageGenRequest imageGenRequest) {
        ImageOptions options = ImageOptionsBuilder.builder()
                .withModel("dall-e-3")
                .build();
        ImagePrompt imagePrompt = new ImagePrompt(imageGenRequest.prompt(), options);
        System.out.println("ImageModel - STARTED!!! - " + imageGenRequest.prompt());
        ImageResponse response = imageModel.call(imagePrompt);
        System.out.println("ImageModel - DONE!!! " + response);
        return "redirect:" + response.getResult().getOutput().getUrl();
    }
}
