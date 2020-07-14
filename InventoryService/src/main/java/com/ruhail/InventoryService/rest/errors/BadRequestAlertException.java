package com.ruhail.InventoryService.rest.errors;

public class BadRequestAlertException extends Throwable {

	private String message;
	private String entityName;
	private String errorKey;

	public BadRequestAlertException(String message, String entityName, String errorKey) {
		this.message = message;
		this.entityName = entityName;
		this.errorKey = errorKey;
	}

}
