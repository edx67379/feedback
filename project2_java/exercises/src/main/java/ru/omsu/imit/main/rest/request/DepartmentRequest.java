package ru.omsu.imit.main.rest.request;

public class DepartmentRequest {
    private String nameDepartment;
    private int idOrganization;
    private String nameOrganization;

    public DepartmentRequest(String nameDepartment, int idOrganization, String nameOrganization) {
        this.nameDepartment = nameDepartment;
        this.idOrganization = idOrganization;
        this.nameOrganization = nameOrganization;
    }

    protected DepartmentRequest() {
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public int getIdOrganization() {
        return idOrganization;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }
}
