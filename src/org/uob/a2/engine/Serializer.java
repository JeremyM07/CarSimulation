package org.uob.a2.engine;

import org.uob.a2.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class Serializer {

    
    public static String mapToString(Map<ResourceType, Integer> map) {
        if (map == null || map.isEmpty()) {return "NONE";}
        return map.entrySet().stream()
                .map(e -> e.getKey() + ":" + e.getValue())
                .collect(Collectors.joining(";"));
    }

    
    public static Map<ResourceType, Integer> stringToMap(String str) {
        Map<ResourceType, Integer> map = new HashMap<>();
        if (str == null || str.equals("NONE") || str.isEmpty()) {return map;}

        String[] pairs = str.split(";");
        for (String pair : pairs) {
            try {
                String[] parts = pair.split(":");
                ResourceType key = ResourceType.valueOf(parts[0]);
                int val = Integer.parseInt(parts[1]);
                map.put(key, val);
            } catch (Exception e) {
            }
        }
        return map;
    }
}