package ru.omsu.imit.main.rest.response;

public class OrganizationResponse extends BaseResponseObject{
	private int id;
    private String name;
    
    public OrganizationResponse(int id, String name) {
		super();
		this.id = id;
		this.name = name;
    }

	protected OrganizationResponse() {
    }

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

}