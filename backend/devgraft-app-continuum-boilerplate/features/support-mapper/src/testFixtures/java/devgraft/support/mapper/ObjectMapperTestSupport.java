package devgraft.support.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperTestSupport {
    private final ObjectMapper objectMapper;
    protected ObjectMapperTestSupport() {
        objectMapper = new ObjectMapperConfig().objectMapper();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
