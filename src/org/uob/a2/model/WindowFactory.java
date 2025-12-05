package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;

public class WindowFactory extends Converter implements Tickable {
    
    private final ResourceType input2 = ResourceType.GLASS;
    private final int input2Amount = 10;
    private boolean stopConvert = false;
    
    public WindowFactory() {
        super("Window Factory",ResourceType.ENGINEER, 2, ResourceType.WINDOW,3);
        this.addCost(ResourceType.CREDITS, 100);
    }
    public void startConvert(){
        this.stopConvert = false;
    }

    public ResourceType getInput2(){
        return input2;
    }

    public int getInput2Amount(){
        return input2Amount;
    }

    public void tick(Context ctx){
        if (ctx.state().getResource(this.getOutput()) <= 5){
            this.startConvert();
        }
        this.convert(ctx);
    }

 
    @Override
    public void convert(Context ctx){
        if (ctx.state().getResource(this.getInput()) >= this.getInputAmount() && !stopConvert && ctx.state().getResource(this.getInput2()) >= this.getInput2Amount()){
            ctx.state().addResource(this.getOutput(), this.getOutputAmount());
            String message = this.getInput().name() + "("+this.getInputAmount()+")"+ " + " + this.getInput2().name() + "(" +this.getInput2Amount()+")" + " -> " + this.getName() + " -> " + this.getOutput().name() + "(" + this.getOutputAmount() + ")";
            System.out.println(message);// Conversion message
            ctx.state().removeResource(this.getInput(), this.getInputAmount());
            ctx.state().removeResource(this.getInput2(), this.getInput2Amount());
            if (ctx.state().getResource(this.getOutput()) > 5){ //Only need 6 WINDOWS per car manufacture cycle
                stopConvert = true;
            }
        }
    }

    @Override
    public String toCSV(){
        return this.getName() + "," + this.getInput() + "," + this.getInputAmount() + "," + this.getInput2() + "," + this.getInput2Amount() + "," + this.getOutput() + "," + this.getOutputAmount() + "," + Saver.MapToString(this.getCosts());
    }
}