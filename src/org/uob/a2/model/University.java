package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import org.uob.a2.data.Saver;


public class University extends Producer implements Tickable{

    public University(){
        super("University", ResourceType.ENGINEER, 1);
        this.addCost(ResourceType.CREDITS, 100);
    }

    @Override
    public void produce(Context ctx){
        ctx.state().addResource(this.getProduct(), this.getAmount());
        String message = this.getName() + " -> " + this.getProduct().name() + "(" + ctx.state().getResource(this.getProduct()) + ")";
        System.out.println(message);    }

    public void tick(Context ctx){
        this.produce(ctx);
    }
    
    @Override
    public String toCSV(){
        return this.getName() + "," + this.getProduct() + "," + this.getAmount() + "," + Saver.MapToString(this.getCosts());
    }
}