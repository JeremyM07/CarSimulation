package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import org.uob.a2.data.Saver;

public class Furnace extends Converter implements Tickable{
    
    private final ResourceType input2 = ResourceType.SAND;
    private final int input2Amount = 8;

    public Furnace(){
        super("Furnace", ResourceType.COAL_ORE, 4, ResourceType.GLASS, 4);
        this.addCost(ResourceType.CREDITS, 100);
    }

    public ResourceType getInput2(){
        return input2;
    }

    public int getInput2Amount(){
        return input2Amount;
    }


    @Override
    public void convert(Context ctx){
        if (ctx.state().getResource(this.getInput()) >= this.getInputAmount() && ctx.state().getResource(this.getInput2()) >= this.getInput2Amount()){
            ctx.state().addResource(this.getOutput(), this.getOutputAmount());
            String message = this.getInput().name() + "("+this.getInputAmount()+")"+ " + " + this.getInput2().name() + "(" +this.getInput2Amount()+")" + " -> " + this.getName() + " -> " + this.getOutput().name() + "(" + ctx.state().getResource(this.getOutput()) + ")";
            System.out.println(message);// Conversion message
            ctx.state().removeResource(this.getInput(), this.getInputAmount());
            ctx.state().removeResource(this.getInput2(), this.getInput2Amount());
        }
    }

    public void tick(Context ctx){
        this.convert(ctx);
    }

    @Override
    public String toCSV(){
        return this.getName() + "," + this.getInput() + "," + this.getInputAmount() + "," + this.getInput2() + "," + this.getInput2Amount() + "," + this.getOutput() + "," + this.getOutputAmount() + "," + Saver.MapToString(this.getCosts());
    }

    
    
}