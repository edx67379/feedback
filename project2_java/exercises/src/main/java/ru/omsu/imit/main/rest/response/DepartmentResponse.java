package ru.omsu.imit.main.rest.response;

public class DepartmentResponse {
    private int idDepartment;
    private String nameDepartment;
    private int idOrganization;
    private String nameOrganization;

    public DepartmentResponse(int idDepartment, String nameDepartment, int idOrganization, String nameOrganization) {
        super();
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
        this.idOrganization = idOrganization;
        this.nameOrganization = nameOrganization;
    }

    protected DepartmentResponse() {
    }

    public int getIdDepartment() {return  idDepartment;}

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
