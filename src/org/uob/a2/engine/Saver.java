package org.uob.a2.engine;
import java.util.stream.Collectors;
import java.util.Map;
import org.uob.a2.model.ResourceType;

public class Saver{

    

    public static String MapToString(Map<ResourceType, Integer> map){
        if (map == null || map.isEmpty()){
            return "";
        }else{
            String csv = map.entrySet().stream().filter(x -> x.getValue() != null).map(x -> x.getKey().name() + "=" + x.getValue())
                                                                                  .collect(Collectors.joining(";"));
            return csv;
        }
    }
}

    