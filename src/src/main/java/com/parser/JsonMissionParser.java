package com.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Mission;
import java.io.File;
import java.io.IOException;

public class JsonMissionParser implements IMissionParser {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Mission loadMission(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), Mission.class);
    }
}
