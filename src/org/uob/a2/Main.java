package org.uob.a2;

// import org.uob.a2.engine.SimulationState;
// import org.uob.a2.model.IronMine;
// import org.uob.a2.model.ResourceType;
import org.uob.a2.engine.*;// change to * when finished
import org.uob.a2.model.*;
//import org.uob.a2.parser.*; // change to * when finished


import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        SimulationState state = new SimulationState();
        Engine engine = new Engine(state);
        

        engine.initialiseDefaults();

        System.out.println("Iron before: " + state.getResource(ResourceType.IRON_ORE));

        System.out.println(engine.nextTick());
        System.out.println(engine.nextTick());

        System.out.println("Iron after: " + state.getResource(ResourceType.IRON_ORE));

        
        
    }
   
} 