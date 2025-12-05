package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;

public class WheelFactory extends Converter implements Tickable {
    
    private final ResourceType input2 = ResourceType.STEEL;
    private final int input2Amount = 8;
    private final ResourceType input3 = ResourceType.RUBBER;
    private final int input3Amount = 8;
    private boolean stopConvert = false;
    
    public WheelFactory() {
        super("Wheel Factory",ResourceType.ENGINEER, 4, ResourceType.WHEEL,2);
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

    public ResourceType getInput3(){
        return input3;
    }

    public int getInput3Amount(){
        return input3Amount;
    }

    public void tick(Context ctx){
        if (ctx.state().getResource(this.getOutput()) <= 3){
            this.startConvert();
        }
        this.convert(ctx);
    }

 
    @Override
    public void convert(Context ctx){
        if (ctx.state().getResource(this.getInput()) >= this.getInputAmount() && !stopConvert && ctx.state().getResource(this.getInput2()) >= this.getInput2Amount() && ctx.state().getResource(this.getInput3()) >= this.getInput3Amount()){
            ctx.state().addResource(this.getOutput(), this.getOutputAmount());
            String message = this.getInput().name() + "("+this.getInputAmount()+")"+ " + " + this.getInput2().name() + "(" +this.getInput2Amount()+")" + " + " + this.getInput3().name() + "(" +this.getInput3Amount()+")" + " -> " + this.getName() + " -> " + this.getOutput().name() + "(" + this.getOutputAmount() + ")";
            System.out.println(message);// Conversion message
            ctx.state().removeResource(this.getInput(), this.getInputAmount());
            ctx.state().removeResource(this.getInput2(), this.getInput2Amount());
            ctx.state().removeResource(this.getInput3(), this.getInput3Amount());
            if (ctx.state().getResource(this.getOutput()) > 3){ //Only need 4 wheels per car manufacture cycle
                stopConvert = true;
            }
        }
    }

    @Override
    public String toCSV(){
        return this.getName() + "," + this.getInput() + "," + this.getInputAmount() + "," + this.getInput2() + "," + this.getInput2Amount() + "," + this.getInput3() + "," + this.getInput3Amount() + "," + this.getOutput() + "," + this.getOutputAmount() + "," + Saver.MapToString(this.getCosts());
    }
}