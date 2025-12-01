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

        //Producers
        IronMine iron = new IronMine();
        CopperMine copper = new CopperMine();
        CoalMine coal = new CoalMine();
        Beach beach = new Beach();
        OilReservoir oil = new OilReservoir();
        University university = new University();
        this.state.addProducer(iron);
        this.state.addProducer(copper);
        this.state.addProducer(coal);
        this.state.addProducer(beach);
        this.state.addProducer(oil);
        this.state.addProducer(university);

        //Converters
        ChemicalPlant chem = new ChemicalPlant();
        this.state.addConverter(chem);


        
        


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
        for(Converter c: state.getConverters()){
            c.tick(this.ctx);
        }
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
            for(Converter c: state.getConverters()){
                c.tick(this.ctx);
            }
            
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