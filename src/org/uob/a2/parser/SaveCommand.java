package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import org.uob.a2.engine.Serializer;

import java.io.*;
import java.util.*;

public class SaveCommand extends Command {

    public SaveCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        // 1. Create Data Directory
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        saveInventory(ctx);
        saveEntities(ctx);
        saveUpgrades(ctx);
        return "Game saved to 'data/inventory.csv', 'data/entities.csv' and 'data/upgrades.csv'.";
    }

    private void saveUpgrades(Context ctx){
            // Create a new file "data/upgrades.csv"
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/upgrades.csv", false))) {
            for (ShopItem item : ctx.state().getUpgrades()) {
                writer.println(item.name());
            }
        } catch (IOException e){
            System.out.println("Error saving upgrades");
        }
    }

    private void saveInventory(Context ctx) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/inventory.csv", false))) {// false ensures file is overwritten
            String data = Serializer.mapToString(ctx.state().getInventory());
            writer.println(data);
        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    private void saveEntities(Context ctx) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/entities.csv", false))) {
            // Write Header
            // writer.println("TYPE,NAME,COST,INPUTS,OUTPUTS");

            for (Producer p : ctx.state().getProducers()){
                writer.println(p.toCSV());
            }
            for (Converter c : ctx.state().getConverters()){
                writer.println(c.toCSV());
            }
            for (Consumer c : ctx.state().getConsumers()){
                writer.println(c.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving entities.");
        }
    }
}