package com.mmad.springai.config;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationAi {

    @Bean
    protected ImageModel imageModel(@Value("${spring.ai.openai.api-key}") String apiKey) {
        return new OpenAiImageModel(new OpenAiImageApi(apiKey));
    }

    @Bean
    protected ChatModel chatModel(@Value("${spring.ai.openai.api-key}") String apiKey) {
        return new OpenAiChatModel(new OpenAiApi(apiKey));
    }
}
