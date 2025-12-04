package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import org.uob.a2.data.Saver;

public class EngineFactory extends Converter implements Tickable {
    
    private final ResourceType input2 = ResourceType.STEEL;
    private final int input2Amount = 9;
    private final ResourceType input3 = ResourceType.COPPER_WIRE;
    private final int input3Amount = 5;
    private boolean stopConvert = false;
    
    public EngineFactory() {
        super("Engine Factory",ResourceType.ENGINEER, 2, ResourceType.ENGINE,1);
        this.addCost(ResourceType.CREDITS, 100);
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
            if (ctx.state().getResource(this.getOutput()) > 0){ //Only need 1 engine per car manufacture cycle
                stopConvert = true;
            }
        }
    }

    @Override
    public String toCSV(){
        return this.getName() + "," + this.getInput() + "," + this.getInputAmount() + "," + this.getInput2() + "," + this.getInput2Amount() + "," + this.getInput3() + "," + this.getInput3Amount() + "," + this.getOutput() + "," + this.getOutputAmount() + "," + Saver.MapToString(this.getCosts());
    }
}