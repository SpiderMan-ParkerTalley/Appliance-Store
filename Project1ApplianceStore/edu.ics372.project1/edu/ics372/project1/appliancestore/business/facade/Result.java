package edu.ics372.project1.appliancestore.business.facade;

/**
 * Ferries the results of operations in the facade to the UI.
 * 
 * @author Jim Sawicki and Sharon Shin
 *
 */
public class Result extends DataTransfer {

	public static final int APPLIANCE_NOT_FOUND = 1;
	public static final int CUSTOMER_NOT_FOUND = 2;
	public static final int BACK_ORDER_NOT_FOUND = 3;
	public static final int OPERATION_SUCCESSFUL = 4;
	public static final int OPERATION_FAILED = 5;
    public static final int BACKORDER_CREATED = 6;

	public int resultCode;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	};

}

