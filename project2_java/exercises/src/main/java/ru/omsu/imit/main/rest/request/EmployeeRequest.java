package ru.omsu.imit.main.rest.request;

public class EmployeeRequest {
    private String nameEmployee;
    private int idDepartment;
    private String nameDepartment;
    private int idOrganization;
    private String nameOrganization;

    public EmployeeRequest(String nameEmployee, int idDepartment, String nameDepartment, int idOrganization, String nameOrganization) {
        this.nameEmployee = nameEmployee;
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
        this.idOrganization = idOrganization;
        this.nameOrganization = nameOrganization;
    }

    protected EmployeeRequest() {
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public int getIdDepartment() {
        return idDepartment;
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
