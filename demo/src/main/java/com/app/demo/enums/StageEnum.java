package com.app.demo.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.TextNode;
import lombok.Getter;

import java.io.IOException;
import java.util.Map;

@Getter
@JsonSerialize(using = StageEnum.StageEnumSerializer.class)
@JsonDeserialize(using = StageEnum.StageEnumDeserializer.class)
public enum StageEnum {

    NOT_STARTED("NOT_STARTED", "msg.enum.notStarted"),
    COMPLETED("COMPLETED", "msg.enum.completed"),
    IN_PROGRESS("IN_PROGRESS", "msg.enum.inProgress");

    private final String title;
    private final String literal;

    StageEnum(String title, String literal) {
        this.title = title;
        this.literal = literal;
    }

    public String getDescription() {
        return this.title;
    }

    public Map<String, String> toJson() {
        return Map.of(
                "title", this.title,
                "literal", this.literal,
                "description", this.getDescription()
        );
    }

    public static class StageEnumDeserializer extends JsonDeserializer<StageEnum> {
        @Override
        public StageEnum deserialize(JsonParser p, DeserializationContext context) throws IOException {
            return StageEnum.valueOf(
                    p.getValueAsString() != null ?
                            p.getValueAsString() :
                            ((TextNode) p.readValueAsTree().get("title")).textValue()
            );
        }
    }

    public static class StageEnumSerializer extends JsonSerializer<StageEnum> {
        @Override
        public void serialize(StageEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeObject(value.toJson());
        }
    }
}
