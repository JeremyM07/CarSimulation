package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import org.uob.a2.data.Saver;

public class DashboardFactory extends Converter implements Tickable {
    
    private final ResourceType input2 = ResourceType.GLASS;
    private final int input2Amount = 4;
    private final ResourceType input3 = ResourceType.COPPER_WIRE;
    private final int input3Amount = 12;
    private final ResourceType input4 = ResourceType.PLASTIC;
    private final int input4Amount = 10;
    private boolean stopConvert = false;
    
    public DashboardFactory() {
        super("Dashboard Factory",ResourceType.ENGINEER, 3, ResourceType.DASHBOARD,1);
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

    public ResourceType getInput4(){
        return input4;
    }

    public int getInput4Amount(){
        return input4Amount;
    }

    public void tick(Context ctx){
        this.convert(ctx);
    }

 
    @Override
    public void convert(Context ctx){
        if (ctx.state().getResource(this.getInput()) >= this.getInputAmount() && !stopConvert && ctx.state().getResource(this.getInput2()) >= this.getInput2Amount() && ctx.state().getResource(this.getInput3()) >= this.getInput3Amount() && ctx.state().getResource(this.getInput4()) >= this.getInput4Amount()){
            ctx.state().addResource(this.getOutput(), this.getOutputAmount());
            String message = this.getInput().name() + "("+this.getInputAmount()+")"+ " + " + this.getInput2().name() + "(" +this.getInput2Amount()+")" + " + " + this.getInput3().name() + "(" +this.getInput3Amount()+")" + " + " + this.getInput4().name() + "(" + this.getInput4Amount() +")" + " -> " + this.getName() + " -> " + this.getOutput().name() + "(" + this.getOutputAmount() + ")";
            System.out.println(message);// Conversion message
            ctx.state().removeResource(this.getInput(), this.getInputAmount());
            ctx.state().removeResource(this.getInput2(), this.getInput2Amount());
            ctx.state().removeResource(this.getInput3(), this.getInput3Amount());
            ctx.state().removeResource(this.getInput4(), this.getInput4Amount());
            if (ctx.state().getResource(this.getOutput()) > 0){ //Only need 1 Dash per car manufacture cycle
                stopConvert = true;
            }
        }
    }

    @Override
    public String toCSV(){
        return this.getName() + "," + this.getInput() + "," + this.getInputAmount() + "," + this.getInput2() + "," + this.getInput2Amount() + "," + this.getInput3() + "," + this.getInput3Amount() + "," + this.getInput4() + "," + this.getInput4Amount() + "," + this.getOutput() + "," + this.getOutputAmount() + "," + Saver.MapToString(this.getCosts());
    }
}