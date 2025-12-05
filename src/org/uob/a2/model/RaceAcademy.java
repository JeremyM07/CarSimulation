package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;

public class RaceAcademy extends Converter implements Tickable{

    public RaceAcademy(){
        super("Race Academy", ResourceType.ENGINEER, 5, ResourceType.DRIVER, 1);
        this.addCost(ResourceType.CREDITS, 100);
    }


    @Override
    public void convert(Context ctx){
        if (ctx.state().getResource(this.getInput()) >= this.getInputAmount() && ctx.state().getResource(this.getOutput()) < 1){// If current drivers in simulation is 1, this wont convert engineers (engineers needed for final build parts and only 1 driver is needed for final car)
            ctx.state().addResource(this.getOutput(), this.getOutputAmount());
            String message = this.getInput().name() + "("+this.getInputAmount()+")"+ " -> " + this.getName() + " -> " + this.getOutput().name() + "(" + this.getOutputAmount() + ")";
            System.out.println(message); // Conversion message
            ctx.state().removeResource(this.getInput(), this.getInputAmount());
        }
    }

    public void tick(Context ctx){
        this.convert(ctx);
    }

    @Override
    public String toCSV(){
        return this.getName() + "," + this.getInput() + "," + this.getInputAmount() + "," + this.getOutput() + "," + this.getOutputAmount() + "," + Saver.MapToString(this.getCosts());
    }

    
    
}