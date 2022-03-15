package edu.ics372.project1.appliancestore.business.facade;

/**
 * The Request class is used to ferry data between the UI and the
 * ApplianceStore facade. It is a singleton.
 * @author Jim Sawicki and Sharon Shin
 */
public class Request extends DataTransfer {
    private static Request request;
    private double repairPlanAmount;
    private double capacity;
    private double maxHeatingOutput;

    /**
     * Private constructor to support the singleton pattern.
     */
    private Request() {
    }
    
    /**
     * Returns the only allowed instance of the class.
     * @return the only instance
     */
    public static Request instance() {
        if (request == null) {
            request =  new Request();
        }
            return request;
    }

    public double getRepairPlanAmount() {
        return repairPlanAmount;
    }

    public void setRepairPlanAmount(double repairPlanAmount) {
        this.repairPlanAmount = repairPlanAmount;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getMaxHeatingOutput() {
        return maxHeatingOutput;
    }

    public void setMaxHeatingOutput(double maxHeatingOutput) {
        this.maxHeatingOutput = maxHeatingOutput;
    } 


    

}