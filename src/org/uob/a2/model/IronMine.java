package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import org.uob.a2.data.Saver;


public class IronMine extends Producer implements Tickable{

    public IronMine(){
        super("Iron Mine", ResourceType.IRON_ORE, 1);
        this.addCost(ResourceType.CREDITS, 100);
    }

    @Override
    public void produce(Context ctx){
        ctx.state().addResource(this.getProduct(), this.getAmount());
        String message = this.getName() + " -> " + this.getProduct().name() + "(" + this.getAmount() + ")";
        System.out.println(message);    }

    public void tick(Context ctx){
        this.produce(ctx);
    }
    
    @Override
    public String toCSV(){
        return this.getName() + "," + this.getProduct() + "," + this.getAmount() + "," + Saver.MapToString(this.getCosts());
    }
}