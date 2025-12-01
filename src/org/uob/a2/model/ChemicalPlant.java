package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import org.uob.a2.data.Saver;

public class ChemicalPlant extends Converter implements Tickable{
    
    private final ResourceType output2 = ResourceType.PLASTIC;
    private final int output2Amount = 5;

    public ChemicalPlant(){
        super("Chemical Plant", ResourceType.CRUDE_OIL, 2, ResourceType.RUBBER, 4);
        this.addCost(ResourceType.CREDITS, 100);
    }

    public ResourceType getOutput2(){
        return output2;
    }

    public int getOutput2Amount(){
        return output2Amount;
    }


    @Override
    public void convert(Context ctx){
        if (ctx.state().getResource(this.getInput()) >= this.getInputAmount()){
            ctx.state().addResource(this.getOutput(), this.getOutputAmount());
            ctx.state().addResource(this.getOutput2(), this.getOutput2Amount());
            String message = this.getInput().name() + "("+ctx.state().getResource(this.getInput())+")"+ " -> " + this.getName() + " -> " + this.getOutput().name() + "(" + ctx.state().getResource(this.getOutput()) + ")" + " + " + this.getOutput2().name() + "(" + ctx.state().getResource(this.getOutput2())+")";
            System.out.println(message);
            ctx.state().removeResource(this.getInput(), this.getInputAmount());
        }
    }

    public void tick(Context ctx){
        this.convert(ctx);
    }

    @Override
    public String toCSV(){
        return this.getName() + "," + this.getInput() + "," + this.getInputAmount() + "," + this.getOutput() + "," + this.getOutputAmount() + "," + this.getOutput2() + "," + this.getOutput2Amount() + "," + Saver.MapToString(this.getCosts());
    }

    
    
}