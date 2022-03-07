package edu.ics372.project1.appliancestore.business.collections;

import java.util.LinkedList;
import java.util.List;

import edu.ics372.project1.appliancestore.business.entities.Appliance;

public class ModelList {
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

    public boolean insertModel(edu.ics372.project1.appliancestore.business.entities.Appliance appliance) {
		models.add(appliance);
		return true;
	}
}