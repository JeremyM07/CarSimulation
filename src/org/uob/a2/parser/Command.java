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
            ctx.state().addProducer(new Producer(super.words.get(1).toUpperCase(), ResourceType.SAND,1));
            return "BEACH has been built!"; // Might need to remove this... can't build beaches
        }else if (super.words.get(1).equals("iron")){
            ctx.state().addProducer(new Producer("IRON MINE", ResourceType.IRON_ORE,1));
            return "IRON MINE has been built!";
        }else if (super.words.get(1).equals("coal")){
            ctx.state().addProducer(new Producer("COAL MINE", ResourceType.COAL_ORE,1));
            return "COAL MINE has been built!";
        }else if (super.words.get(1).equals("copper")){
            ctx.state().addProducer(new Producer("COPPER MINE", ResourceType.COPPER_ORE,1));
            return "COPPER MINE has been built!";
        }else if (super.words.get(1).equals("oil")){
            ctx.state().addProducer(new Producer("OIL RESERVOIR", ResourceType.CRUDE_OIL,1));
            return "OIL RESERVOIR has been built!";
        }else if (super.words.get(1).equals("university")){
            ctx.state().addProducer(new Producer(super.words.get(1).toUpperCase(), ResourceType.ENGINEER,1));
            return "UNIVERSITY has been built!";
        }
        // CONVERTERS
        else if (super.words.get(1).equals("smelter")){
            ctx.state().addConverter(new Converter(super.words.get(1).toUpperCase(), ResourceType.IRON_ORE,9,ResourceType.STEEL, 4));
            return "SMELTER has been built!";
        }else if (super.words.get(1).equals("chemical") && super.words.get(2).equals("plant")){
            ctx.state().addConverter(new Converter("CHEMICAL PLANT (PLASTIC)", ResourceType.CRUDE_OIL,10,ResourceType.PLASTIC, 5));
            ctx.state().addConverter(new Converter("CHEMICAL PLANT (RUBBER)", ResourceType.CRUDE_OIL,8,ResourceType.RUBBER, 4));    
            return "CHEMICAL PLANT(s) has been built!";
        }else if (super.words.get(1).equals("extruder")){
            return "EXTRUDER has been built!";
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
        }else if (super.words.get(1).equals("race") && super.words.get(2).equals("academy")){
            return "RACE ACADEMY has been built!";
        }
    }

    
}