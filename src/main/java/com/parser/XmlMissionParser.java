package com.parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.model.Mission;
import java.io.File;
import java.io.IOException;

public class XmlMissionParser implements IMissionParser {
    private XmlMapper mapper = new XmlMapper();

    @Override
    public Mission loadMission(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), Mission.class);
    }
}
