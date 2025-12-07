package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;

public class Extruder extends Converter implements Tickable{

    public Extruder(){
        super("Extruder", ResourceType.COPPER_ORE, 4, ResourceType.COPPER_WIRE, 8);
        this.addCost(ResourceType.CREDITS, 100);
    }


    @Override
    public void convert(Context ctx){
        if (ctx.state().getResourceAmount(this.getInput()) >= this.getInputAmount()){
            ctx.state().addResource(this.getOutput(), this.getOutputAmount());
            String message = this.getInput().name() + "("+this.getInputAmount()+")"+ " -> " + this.getName() + " -> " + this.getOutput().name() + "(" + this.getOutputAmount() + ")";
            System.out.println(message);// Conversion message
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