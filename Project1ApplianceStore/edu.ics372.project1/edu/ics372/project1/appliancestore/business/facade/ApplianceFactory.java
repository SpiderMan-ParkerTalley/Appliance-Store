package edu.ics372.project1.appliancestore.business.facade;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.Washer;

public class ApplianceFactory {
    public static final int WASHER = 1;
	public static final int DRYER = 2;
	public static final int KITCHENRANGE = 3;
	public static final int REFRIDGERATOR = 4;
	public static final int FURNACE = 5;
	public static final int DISHWASHER = 6;

    public Appliance createAppliance(int type, String model, String brand, int price){
        switch(type) {
            case WASHER: return new Washer(brand, model, price, 0, hasRepairPlan, repairPlanAmount))
        }
    }
}