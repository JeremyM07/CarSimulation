package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import org.uob.a2.engine.Serializer;

import java.io.*;
import java.util.*;

public class LoadCommand extends Command {

    public LoadCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        File invFile = new File("data/inventory.csv");
        File entFile = new File("data/entities.csv");

        if (!invFile.exists() || !entFile.exists()) {
            return "No save data found.";
        }

        loadInventory(ctx, invFile);
        loadEntities(ctx, entFile);
        
        return "Game loaded successfully.";
    }

    private void loadInventory(Context ctx, File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line != null) {
                Map<ResourceType, Integer> data = Serializer.stringToMap(line);
                ctx.state().getInventory().clear();
                ctx.state().getInventory().putAll(data);
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory.");
        }
    }

    private void loadEntities(Context ctx, File file) {
        ctx.state().getProducers().clear();// wipe entities
        ctx.state().getConverters().clear();
        ctx.state().getConsumers().clear();


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // br.readLine(); // skip Header
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 15) continue;

                String name = parts[0];
                
                // Recreate the specific object
                Entity e = createEntityByName(name);

                if (e != null) {
                    if (e instanceof Producer){
                        Producer p = (Producer) e;
                        ctx.state().addProducer(p);
                    }else if (e instanceof Converter){
                        Converter c = (Converter) e;
                        ctx.state().addConverter(c);
                    }else{
                        Consumer c = (Consumer) e;
                        ctx.state().addConsumer(c);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading entities.");
        }
    }

    // MAPS STRINGS BACK TO CLASSES
    private Entity createEntityByName(String name) {
        switch (name.toLowerCase()) {
            // Producers
            case "iron mine":       return new IronMine();
            case "copper mine":     return new CopperMine();
            case "coal mine":       return new CoalMine();
            case "beach":           return new Beach();
            case "oil reservoir":   return new OilReservoir();
            case "university":      return new University();
            
            // Converters
            case "chemical plant":  return new ChemicalPlant();
            case "extruder":        return new Extruder();
            case "furnace":         return new Furnace();
            case "race academy":    return new RaceAcademy();
            case "smelter":         return new Smelter();
            case "refinery":        return new Refinery();
            case "car assembler":   return new CarAssembler();
            case "chassis factory": return new ChassisFactory();
            case "dashboard factory": return new DashboardFactory();
            case "engine factory":  return new EngineFactory();
            case "seat factory":    return new SeatFactory();
            case "wheel factory":   return new WheelFactory();
            case "window factory":  return new WindowFactory();
            
            // Consumers
            case "race track":      return new RaceTrack();
            
            default: return null;
        }
    }
}