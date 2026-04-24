package com.parser;

import com.model.Mission;
import java.io.IOException;

public interface IMissionParser {
    Mission loadMission(String filePath) throws IOException;
}
