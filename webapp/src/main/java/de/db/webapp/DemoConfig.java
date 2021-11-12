package de.db.webapp;

import java.util.HashMap;
import java.util.Map;

public class DemoConfig {

    private String gruss;
    private String stadt;

    public Map<String, String> getDemoValues() {
        Map<String, String> result = new HashMap<>();
        result.put("gruss",gruss);
        result.put("stadt",stadt);
        return result;
    }
}
