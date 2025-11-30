package org.uob.a2.engine;

import org.uob.a2.model.*;
import org.uob.a2.engine.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Engine {
    private int tick;
    protected SimulationState state;

    public Engine(){
        this.state = new SimulationState();
        this.tick = 0;
    }


    public Engine(SimulationState state){
        this.state = state;
        this.tick = 0;
        
    }
    
    // USED FOR TESTING
    public void initialiseDefaults() {
        //ADD ALL THE ENTITIES YOU CREATE TO THE PRODUCER, CONVERTER AND CONSUMER LISTS IN SIMULATIONSTATE HERE.
        IronMine iron = new IronMine();
        this.state.addProducer(iron);     


        //...
        this.state.updateHistory();
    }

    public int getCurrentTick(){
        return this.tick;
    }

    public SimulationState getState(){
        return this.state;
    }

    public String nextTick(Context ctx){
        for(Producer p : state.getProducers()){
            p.tick(ctx);
        }

        //...
        this.tick++;
        this.state.updateHistory();
        return "Tick : " + this.getCurrentTick();
    }
    

    

}