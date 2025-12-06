package org.uob.a2;
import java.util.Random;
import java.util.Scanner;
// import org.uob.a2.engine.SimulationState;
// import org.uob.a2.model.IronMine;
// import org.uob.a2.model.ResourceType;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import org.uob.a2.parser.*; // change when finished
import java.util.*;
import java.io.*;


import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        SimulationState state = new SimulationState();
        Engine engine = new Engine(state);
        Context context = new Context(engine,state);
        
        boolean done = false;
        Scanner input = new Scanner(System.in);
        Parser parser = new Parser();
        while (!done){
            
            System.out.print(">> ");
            Command cmd = parser.parse(input.nextLine());
            String output = cmd.execute(context);
            System.out.println(output);
            if (output.equals("Simulation ending...")){
                done = true;
            }
            System.out.print("\n+++++++++++++++++++++++++\n");

                
        }
        
        // engine.initialiseDefaults();


        // System.out.println(engine.nextTick(125));
        // System.out.println(state.getInventory());



        
        
    }
   
} 