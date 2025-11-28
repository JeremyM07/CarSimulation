package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import java.util.List;

public abstract class Command {

    protected List<String> words;
   

    public Command (List<String> words) {
       
        this.words = words;
    }

    public Command () {}
    
    public abstract String execute(Context ctx);
}

public class BuildCommand extends Command{
    

    public String execute(Context ctx){
        // PRODUCERS
        if (super.words.get(1).equals("beach")){
            
            return "BEACH has been built!"; // Might need to remove this... can't build beaches
        }else if (super.words.get(1).equals("iron")){
            return "IRON MINE has been built!";
        }else if (super.words.get(1).equals("coal")){
            return "COAL MINE has been built!";
        }else if (super.words.get(1).equals("copper")){
            return "COPPER MINE has been built!";
        }else if (super.words.get(1).equals("oil")){
            return "OIL RESERVOIR has been built!";
        }else if (super.words.get(1).equals("university")){
            return "UNIVERSITY has been built!";
        }
        // CONVERTERS
        else if (super.words.get(1).equals("smelter")){
            return "SMELTER has been built!";
        }else if (super.words.get(1).equals("chemical") && super.words.get(2).equals("plant")){
            return "CHEMICAL PLANT has been built!";
        }else if (super.words.get(1).equals("extruder")){
            return "EXTRUDER has been built!";
        }else if (super.words.get(1).equals("race") && super.words.get(2).equals("academy")){
            return "RACE ACADEMY has been built!";
        }else if (super.words.get(1).equals("furnace")){
            return "FURNACE has been built!";
        }else if (super.words.get(1).equals("copper") && super.words.get(2).equals("mine")){
            return "IRON MINE has been built!";
        }else if (super.words.get(1).equals("refinery")){
            return "REFINERY has been built!"; //crude oil -> petrol
        }
        // CONSUMERS
        else if (super.words.get(1).equals("race") && super.words.get(2).equals("car")){
            return "RACE CAR has been built! Level increased.";
        }
    }

    
}