package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;

public class CarAssembler extends Converter implements Tickable {
    
    private final ResourceType input2 = ResourceType.CHASSIS;
    private final int input2Amount = 1;
    private final ResourceType input3 = ResourceType.DASHBOARD;
    private final int input3Amount = 1;
    private final ResourceType input4 = ResourceType.PETROL;
    private final int input4Amount = 20;
    private final ResourceType input5 = ResourceType.ENGINE;
    private final int input5Amount = 1;
    private final ResourceType input6 = ResourceType.WHEEL;
    private final int input6Amount = 4;
    private final ResourceType input7 = ResourceType.WINDOW;
    private final int input7Amount = 6;
    private final ResourceType input8 = ResourceType.SEAT;
    private final int input8Amount = 2;
    private boolean stopConvert = false;
    
    public CarAssembler() {
        super("Car Assembler",ResourceType.DRIVER, 1, ResourceType.RACE_CAR,1);
        this.addCost(ResourceType.CREDITS, 500);
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

    public ResourceType getInput4(){
        return input4;
    }

    public int getInput4Amount(){
        return input4Amount;
    }

    public ResourceType getInput5(){
        return input5;
    }

    public int getInput5Amount(){
        return input5Amount;
    }

    public ResourceType getInput6(){
        return input6;
    }

    public int getInput6Amount(){
        return input6Amount;
    }

    public ResourceType getInput7(){
        return input7;
    }

    public int getInput7Amount(){
        return input7Amount;
    }
    public ResourceType getInput8(){
        return input8;
    }

    public int getInput8Amount(){
        return input8Amount;
    }

    public void tick(Context ctx){
        if (ctx.state().getResource(this.getOutput()) < 1){
            this.startConvert();
        }
        this.convert(ctx);
    }

 
    @Override
    public void convert(Context ctx){
        if (ctx.state().getResource(this.getInput()) >= this.getInputAmount() && !stopConvert && ctx.state().getResource(this.getInput2()) >= this.getInput2Amount() && ctx.state().getResource(this.getInput3()) >= this.getInput3Amount() && ctx.state().getResource(this.getInput4()) >= this.getInput4Amount() && ctx.state().getResource(this.getInput5()) >= this.getInput5Amount() && ctx.state().getResource(this.getInput6()) >= this.getInput6Amount() && ctx.state().getResource(this.getInput7()) >= this.getInput7Amount() && ctx.state().getResource(this.getInput8()) >= this.getInput8Amount()){
            ctx.state().addResource(this.getOutput(), this.getOutputAmount());
            String message = this.getInput().name() + "("+this.getInputAmount()+")" + " + " + this.getInput2().name() + "(" +this.getInput2Amount()+")" + " + " + this.getInput3().name() + "(" +this.getInput3Amount()+")" + " + " + this.getInput4().name() + "(" + this.getInput4Amount() +")" + " + " + this.getInput5().name() + "(" +this.getInput5Amount()+")" + " + " + this.getInput6().name() + "(" +this.getInput6Amount()+")" + " + " + this.getInput7().name() + "(" +this.getInput7Amount()+")" + " + " + this.getInput8().name() + "(" +this.getInput8Amount()+")" +" -> " + this.getName() + " -> " + this.getOutput().name() + "(" + this.getOutputAmount() + ")";
            System.out.println(message);// Conversion message
            ctx.state().removeResource(this.getInput(), this.getInputAmount());
            ctx.state().removeResource(this.getInput2(), this.getInput2Amount());
            ctx.state().removeResource(this.getInput3(), this.getInput3Amount());
            ctx.state().removeResource(this.getInput4(), this.getInput4Amount());
            ctx.state().removeResource(this.getInput5(), this.getInput5Amount());
            ctx.state().removeResource(this.getInput6(), this.getInput6Amount());
            ctx.state().removeResource(this.getInput7(), this.getInput7Amount());
            ctx.state().removeResource(this.getInput8(), this.getInput8Amount());
            if (ctx.state().getResource(this.getOutput()) > 0){ //Only need 1 car per car manufacture cycle
                stopConvert = true;
            }
        }
    }

    @Override
    public String toCSV(){
        return this.getName() + "," + this.getInput() + "," + this.getInputAmount() + "," + this.getInput2() + "," + this.getInput2Amount() + "," + this.getInput3() + "," + this.getInput3Amount() + "," + this.getInput4() + "," + this.getInput4Amount() + "," + this.getInput5() + "," + this.getInput5Amount()  + "," + this.getInput6()  + ","  + this.getInput7() + "," + this.getInput7Amount()  + "," + this.getInput8() + "," + this.getInput8Amount()  + "," + this.getOutput() + "," + this.getOutputAmount() + "," + Saver.MapToString(this.getCosts());
    }
}