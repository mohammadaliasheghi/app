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
@JsonSerialize(using = GenderEnum.GenderEnumSerializer.class)
@JsonDeserialize(using = GenderEnum.GenderEnumDeserializer.class)
public enum GenderEnum {

    MALE("MALE", "msg.enum.male"),
    FEMALE("FEMALE", "msg.enum.female");

    private final String title;
    private final String literal;

    GenderEnum(String title, String literal) {
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

    public static class GenderEnumDeserializer extends JsonDeserializer<GenderEnum> {
        @Override
        public GenderEnum deserialize(JsonParser p, DeserializationContext context) throws IOException {
            return GenderEnum.valueOf(
                    p.getValueAsString() != null ?
                            p.getValueAsString() :
                            ((TextNode) p.readValueAsTree().get("title")).textValue()
            );
        }
    }

    public static class GenderEnumSerializer extends JsonSerializer<GenderEnum> {
        @Override
        public void serialize(GenderEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeObject(value.toJson());
        }
    }
}
