package ru.omsu.imit.main.rest.request;

public class OrganizationRequest {

    private String name;
    
    public OrganizationRequest(String name) {
		super();
		this.name = name;
    }

	protected OrganizationRequest() {
    }

	public String getName() {
		return name;
	}

}