package ru.omsu.imit.main.rest.response;

public class EmployeeResponse {
    private int idEmployee;
    private String nameEmployee;
    private int idDepartment;
    private String nameDepartment;
    private int idOrganization;
    private String nameOrganization;

    public EmployeeResponse(int idEmployee, String nameEmployee, int idDepartment, String nameDepartment, int idOrganization, String nameOrganization) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
        this.idOrganization = idOrganization;
        this.nameOrganization = nameOrganization;
    }

    protected EmployeeResponse() {
    }

    public int getIdEmployee() {
        return idEmployee;
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
