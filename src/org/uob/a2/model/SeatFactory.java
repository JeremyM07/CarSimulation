package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import org.uob.a2.data.Saver;

public class SeatFactory extends Converter implements Tickable {
    
    private final ResourceType input2 = ResourceType.STEEL;
    private final int input2Amount = 4;
    private final ResourceType input3 = ResourceType.PLASTIC;
    private final int input3Amount = 8;
    private boolean stopConvert = false;
    
    public SeatFactory() {
        super("Seat Factory",ResourceType.ENGINEER, 2, ResourceType.SEAT,1);
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
            if (ctx.state().getResource(this.getOutput()) > 1){ //Only need 2 seats per car manufacture cycle
                stopConvert = true;
            }
        }
    }

    @Override
    public String toCSV(){
        return this.getName() + "," + this.getInput() + "," + this.getInputAmount() + "," + this.getInput2() + "," + this.getInput2Amount() + "," + this.getInput3() + "," + this.getInput3Amount() + "," + this.getOutput() + "," + this.getOutputAmount() + "," + Saver.MapToString(this.getCosts());
    }
}