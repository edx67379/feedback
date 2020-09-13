package ru.omsu.imit.main.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.main.models.Department;
import ru.omsu.imit.main.models.Employee;
import ru.omsu.imit.main.models.Organization;

import java.util.List;

public interface DepartmentMapper {
    @Insert("INSERT INTO department (name, organization_id) VALUES ( #{department.name}, #{organization.id} )")
    @Options(useGeneratedKeys = true, keyProperty = "department.id")
    public Integer insert(@Param("organization")Organization organization, @Param("department")Department department);

    @Select("SELECT * FROM department WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "employeeList", column = "id", javaType = List.class, many = @Many(select = "ru.omsu.imit.main.mappers.EmployeeMapper.getByDepartment", fetchType = FetchType.LAZY) ),
            @Result(property = "organization", column = "organization_id", javaType = Organization.class, one = @One(select = "ru.omsu.imit.main.mappers.OrganizationMapper.getByDepartment", fetchType = FetchType.LAZY) )})
    public Department getById(int id);

    @Select("SELECT * FROM department WHERE organization_id = #{organization.id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "employeeList", column = "id", javaType = List.class, many = @Many(select = "ru.omsu.imit.main.mappers.EmployeeMapper.getByDepartment", fetchType = FetchType.LAZY) ),
            @Result(property = "organization", column = "organization_id", javaType = Organization.class, one = @One(select = "ru.omsu.imit.main.mappers.OrganizationMapper.getByDepartment", fetchType = FetchType.LAZY) )})
    public List<Department> getByOrganization(@Param("organization") Organization organization);

    @Select("SELECT * FROM department WHERE id = #{employee.getDepartment().getId()}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "employeeList", column = "id", javaType = List.class, many = @Many(select = "ru.omsu.imit.main.mappers.EmployeeMapper.getByDepartment", fetchType = FetchType.LAZY) ),
            @Result(property = "organization", column = "organization_id", javaType = Organization.class, one = @One(select = "ru.omsu.imit.main.mappers.OrganizationMapper.getByDepartment", fetchType = FetchType.LAZY) )})
    public Department getByEmployee(@Param("employee")Employee employee);

    @Select("SELECT * FROM department WHERE name = #{name}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "employeeList", column = "id", javaType = List.class, many = @Many(select = "ru.omsu.imit.main.mappers.EmployeeMapper.getByDepartment", fetchType = FetchType.LAZY) ),
            @Result(property = "organization", column = "organization_id", javaType = Organization.class, one = @One(select = "ru.omsu.imit.main.mappers.OrganizationMapper.getByDepartment", fetchType = FetchType.LAZY) )})
    public List<Department> getByName(String name);

    @Select("SELECT * FROM department")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "employeeList", column = "id", javaType = List.class, many = @Many(select = "ru.omsu.imit.main.mappers.EmployeeMapper.getByDepartment", fetchType = FetchType.LAZY) ),
            @Result(property = "organization", column = "organization_id", javaType = Organization.class, one = @One(select = "ru.omsu.imit.main.mappers.OrganizationMapper.getByDepartment", fetchType = FetchType.LAZY) ) })
    public List<Department> getAllLazy();

    @Delete("DELETE FROM department")
    public void deleteAll();

    @Delete("DELETE FROM department WHERE id = #{department.id}")
    public void delete(@Param("department") Department department);

    @Update("UPDATE department SET name = #{name} WHERE id = #{department.id} ")
    public void changeName(@Param("department") Department department, @Param("name") String name);
}
