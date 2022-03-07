package edu.ics372.project1.appliancestore.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.project1.appliancestore.business.entities.Appliance;


public class ModelList implements Iterable<Appliance>, Serializable {
    private static final long serialVersionUID = 1L;
	private List<Appliance> models = new LinkedList<Appliance>();
	private static ModelList modelList;

	private ModelList() {

	}

	public static ModelList getInstance() {
		if (modelList == null) {
			modelList = new ModelList();
		}
		return modelList;
	}

    /**
     * Check whether an appliance with a given appliance id exists.
     * 
     * @param applianceId String the id of the appliance
     * @return Appliance appliance object if found, null otherwise.
     */
    public Appliance search(String applianceId) {
        for (Iterator<Appliance> iterator = models.iterator(); iterator.hasNext();) {
            Appliance appliance = iterator.next();
            if(appliance.getId().equals(applianceId)) {
                return appliance;
            }
        }
        return null;
    }

    /**
     * Inserts an appliance into the collection.
     * 
     * @param appliance Appliance the appliance to be inserted.
     * @return boolean true if the appliance could be inserted.
     */
    public boolean insertModel(Appliance appliance) {
		models.add(appliance);
		return true;
	}

    @Override
    public Iterator<Appliance> iterator() {
        return models.iterator();
    }




}