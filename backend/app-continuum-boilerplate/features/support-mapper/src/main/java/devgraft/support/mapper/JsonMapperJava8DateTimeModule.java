package devgraft.support.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class JsonMapperJava8DateTimeModule extends SimpleModule {
    public JsonMapperJava8DateTimeModule() {
        /*LocalTime Format*/
        this.addSerializer(LocalTime.class, new LocalTimeJsonSerializer());
        this.addDeserializer(LocalTime.class, new LocalTimeJsonDeserializer());
        /*LocalDate Format*/
        this.addSerializer(LocalDate.class, new LocalDateJsonSerializer());
        this.addDeserializer(LocalDate.class, new LocalDateJsonDeserializer());
        /*LocalDateTime Format*/
        this.addSerializer(LocalDateTime.class, new LocalDateTimeJsonSerializer());
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeJsonDeserializer());
    }

    private static class LocalTimeJsonSerializer extends JsonSerializer<LocalTime> {
        @Override
        public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(DateTimeFormatter.ofPattern("HH:mm:ss").format(value));
        }
    }
    private static class LocalTimeJsonDeserializer extends JsonDeserializer<LocalTime> {
        @Override
        public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return LocalTime.parse(p.getValueAsString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
    }

    private static class LocalDateJsonSerializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(value));
        }
    }
    private static class LocalDateJsonDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return LocalDate.parse(p.getValueAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    private static class LocalDateTimeJsonSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(value));
        }
    }
    private static class LocalDateTimeJsonDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return LocalDateTime.parse(p.getValueAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}
