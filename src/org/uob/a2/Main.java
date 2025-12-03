package org.uob.a2;

// import org.uob.a2.engine.SimulationState;
// import org.uob.a2.model.IronMine;
// import org.uob.a2.model.ResourceType;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
//import org.uob.a2.parser.*; // change when finished


import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        SimulationState state = new SimulationState();
        Engine engine = new Engine(state);
        

        engine.initialiseDefaults();


        System.out.println(engine.nextTick(6));



        
        
    }
   
} 