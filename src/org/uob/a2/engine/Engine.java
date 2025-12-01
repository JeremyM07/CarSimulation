package org.uob.a2.engine;

import org.uob.a2.model.*;
import org.uob.a2.engine.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Engine {
    private int tick;
    protected SimulationState state;
    protected Context ctx;

    public Engine(){
        this.state = new SimulationState();
        this.tick = 0;
        this.ctx = new Context(this, this.state);
    }


    public Engine(SimulationState state){
        this.state = state;
        this.tick = 0;
        this.ctx = new Context(this, this.state);
        
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

    public String nextTick(){
        for(Producer p : state.getProducers()){
            p.tick(this.ctx);
        }

        //...
        this.tick++;
        this.state.updateHistory();
        return "Tick : " + this.getCurrentTick();
    }

    // tick by a certain amount
    public String nextTick(int amount){
        for(int i = 0; i < amount; i++){
            
            for(Producer p : state.getProducers()){
                p.tick(this.ctx);
            }
            //...
            this.tick++;
            this.state.updateHistory();
            
        }
        return "Tick : " + this.getCurrentTick();
    }
    

    

}