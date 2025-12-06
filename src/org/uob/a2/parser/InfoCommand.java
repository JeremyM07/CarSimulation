package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.List;
import java.util.Map;

public class InfoCommand extends Command {

    public InfoCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        if (words.size() == 1) {
            return "Current Status:\n" + 
                   "Credits: " + ctx.state().getInventory().getOrDefault(ResourceType.CREDITS, 0) + "\n" +
                   "Inventory: " + ctx.state().getInventory().toString();
        }

        String topic = words.get(1);
        SimulationState state = ctx.state();
        ResourceType type = null;

        switch (topic) {
            // --- RESOURCES ---
            case "iron":
            case "iron_ore":    type = ResourceType.IRON_ORE; break;
            case "copper":
                if (words.size() > 2) {
                    if (words.get(2).equalsIgnoreCase("ore")) { type = ResourceType.COPPER_ORE; break; }
                    else if (words.get(2).equalsIgnoreCase("wire")) { type = ResourceType.COPPER_WIRE; break; }
                }
                return "Copper wire or copper ore? Please specify.";
            case "copper_ore":  type = ResourceType.COPPER_ORE; break;
            case "coal":
            case "coal_ore":    type = ResourceType.COAL_ORE; break;
            case "oil":
            case "crude":
            case "crude_oil":   type = ResourceType.CRUDE_OIL; break;
            case "sand":        type = ResourceType.SAND; break;
            case "engineer":    type = ResourceType.ENGINEER; break;

            case "steel":       type = ResourceType.STEEL; break;
            case "plastic":     type = ResourceType.PLASTIC; break;
            case "rubber":      type = ResourceType.RUBBER; break;
            case "glass":       type = ResourceType.GLASS; break;
            case "wire":
            case "copper_wire": type = ResourceType.COPPER_WIRE; break;
            case "driver":      type = ResourceType.DRIVER; break;

            case "window":      type = ResourceType.WINDOW; break;
            case "chassis":     type = ResourceType.CHASSIS; break;
            case "engine":      type = ResourceType.ENGINE; break;
            case "petrol":      type = ResourceType.PETROL; break;
            case "dash":
            case "dashboard":   type = ResourceType.DASHBOARD; break;
            case "wheel":       type = ResourceType.WHEEL; break;
            case "seat":        type = ResourceType.SEAT; break;

            case "race":
            case "car":
            case "race_car":    type = ResourceType.RACE_CAR; break;
            case "credits":
            case "money":
            case "cash":        type = ResourceType.CREDITS; break;

            // --- ENTITIES ---
            case "mine":
            case "iron_mine":   return getEntityInfo(new IronMine());
            case "copper_mine": return getEntityInfo(new CopperMine());
            case "coal_mine":   return getEntityInfo(new CoalMine());
            case "beach":       return getEntityInfo(new Beach());
            case "reservoir":
            case "oil_well":    return getEntityInfo(new OilReservoir());
            case "uni":
            case "university":  return getEntityInfo(new University());

            case "chem":
            case "chemical_plant": return getEntityInfo(new ChemicalPlant());
            case "extruder":       return getEntityInfo(new Extruder());
            case "furnace":        return getEntityInfo(new Furnace());
            case "academy":
            case "race_academy":   return getEntityInfo(new RaceAcademy());
            case "smelter":        return getEntityInfo(new Smelter());
            case "refinery":       return getEntityInfo(new Refinery());
            case "assembler":
            case "car_assembler":  return getEntityInfo(new CarAssembler());
            case "chassis_fac":    return getEntityInfo(new ChassisFactory());
            case "dash_fac":       return getEntityInfo(new DashboardFactory());
            case "engine_fac":     return getEntityInfo(new EngineFactory());
            case "seat_fac":       return getEntityInfo(new SeatFactory());
            case "wheel_fac":      return getEntityInfo(new WheelFactory());
            case "window_fac":     return getEntityInfo(new WindowFactory());

            case "track":
            case "racetrack":      return getEntityInfo(new RaceTrack());

            default: return "Unknown resource or building: " + topic;
        }

        if (type != null) {
            return getResourceInfo(state, type);
        }
        return "Unknown resource or building: " + topic;
    }

    private String getResourceInfo(SimulationState state, ResourceType type) {
        return type.toString() + ": " + state.getInventory().getOrDefault(type, 0);
    }

    private String getEntityInfo(Entity e) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ").append(e.getName()).append(" ---\n");
        sb.append("Description: ").append(getEntityDescription(e)).append("\n");
        sb.append("Build Cost: ").append(getEntityCost(e)).append("\n"); 

        if (e instanceof Producer) {
            Producer p = (Producer) e;
            sb.append("Type: Producer\n");
            sb.append("Production: ").append(p.getAmount()).append(" x ").append(p.getProduct()).append(" / tick");
        } 
        else if (e instanceof Converter) {
            Converter c = (Converter) e;
            sb.append("Type: Converter\n");
            sb.append("Requires: ").append(getEntityRecipe(e)).append("\n");
            sb.append("Produces: ").append(c.getOutputAmount()).append(" x ").append(c.getOutput());
            if (c instanceof ChemicalPlant){
                ChemicalPlant p = (ChemicalPlant) c;
                sb.append(", ").append(p.getOutput2Amount()).append(" x ").append(p.getOutput2());
            }
        } 
        else if (e instanceof Consumer) {
            sb.append("Type: Consumer\n");
            sb.append("Action: Consumes Race Car for Credits.");
        }
        
        return sb.toString();
    }


    private String getEntityCost(Entity e) {
        if (e instanceof IronMine) return "100 Credits";
        if (e instanceof CopperMine) return "100 Credits";
        if (e instanceof CoalMine) return "100 Credits";
        if (e instanceof Beach) return "100 Credits";
        if (e instanceof OilReservoir) return "100 Credits";
        if (e instanceof University) return "100 Credits";
        
        if (e instanceof ChemicalPlant) return "100 Credits";
        if (e instanceof Extruder) return "100 Credits";
        if (e instanceof Furnace) return "100 Credits";
        if (e instanceof RaceAcademy) return "100 Credits";
        if (e instanceof Smelter) return "100 Credits";
        if (e instanceof Refinery) return "100 Credits";
        if (e instanceof CarAssembler) return "500 Credits";
        if (e instanceof ChassisFactory) return "100 Credits";
        if (e instanceof DashboardFactory) return "100 Credits";
        if (e instanceof EngineFactory) return "100 Credits";
        if (e instanceof SeatFactory) return "100 Credits";
        if (e instanceof WheelFactory) return "100 Credits";
        if (e instanceof WindowFactory) return "100 Credits";
        if (e instanceof RaceTrack) return "100 Credits";
        return "Unknown";
    }

    private String getEntityRecipe(Entity e) {

        if (e instanceof ChemicalPlant) return "6x CRUDE_OIL";
        if (e instanceof Extruder) return "4x COPPER_ORE";
        if (e instanceof Furnace) return "8x SAND, 4x COAL_ORE";
        if (e instanceof RaceAcademy) return "5x ENGINEER"; // Example input
        if (e instanceof Smelter) return "9x IRON_ORE, 4x COAL_ORE";
        if (e instanceof Refinery) return "1x ENGINEER, 2x CRUDE_OIL";
        if (e instanceof CarAssembler) return "1x DRIVER, 1x CHASSIS, 1x ENGINE, 4x WHEEL, 4x SEAT, 1x DASHBOARD, 6x WINDOW, 5x PETROL";
        if (e instanceof ChassisFactory) return "2x ENGINEER, 16x STEEL";
        if (e instanceof DashboardFactory) return "4x PLASTIC, 2x GLASS, 20x COPPER WIRE";
        if (e instanceof EngineFactory) return "9x STEEL, 5x COPPER WIRE, 2x ENGINEER";
        if (e instanceof SeatFactory) return "4x PLASTIC, 4x STEEL, 2x ENGINEER";
        if (e instanceof WheelFactory) return "8x RUBBER, 8x STEEL, 4x ENGINEER";
        if (e instanceof WindowFactory) return "10x GLASS, 2x ENGINEER";
        
        return "None";
    }

    private String getEntityDescription(Entity e) {
        if (e instanceof IronMine) return "Extracts Iron Ore from the ground.";
        if (e instanceof CopperMine) return "Extracts Copper Ore from the ground.";
        if (e instanceof CoalMine) return "Extracts Coal Ore from the ground.";
        if (e instanceof Beach) return "Provides Sand.";
        if (e instanceof OilReservoir) return "Extracts Crude Oil.";
        if (e instanceof University) return "Trains Engineers.";
        
        if (e instanceof ChemicalPlant) return "Produces Plastic and Rubber from Oil.";
        if (e instanceof Extruder) return "Produces Copper Wire from Copper.";
        if (e instanceof Furnace) return "Produces Glass from Sand.";
        if (e instanceof RaceAcademy) return "Trains Engineers into Drivers.";
        if (e instanceof Smelter) return "Smelts Iron Ore into Steel.";
        if (e instanceof Refinery) return "Refines Crude Oil into Petrol.";
        if (e instanceof CarAssembler) return "Assembles the final Race Car.";
        if (e instanceof ChassisFactory) return "Manufactures Car Chassis.";
        if (e instanceof DashboardFactory) return "Manufactures Dashboards.";
        if (e instanceof EngineFactory) return "Manufactures Engines.";
        if (e instanceof SeatFactory) return "Manufactures Seats.";
        if (e instanceof WheelFactory) return "Manufactures Wheels.";
        if (e instanceof WindowFactory) return "Manufactures Windows.";
        
        if (e instanceof RaceTrack) return "A track to race and earn credits.";
        
        return "No description available.";
    }
}