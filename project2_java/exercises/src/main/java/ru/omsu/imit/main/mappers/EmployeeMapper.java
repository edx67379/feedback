package ru.omsu.imit.main.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.main.models.Department;
import ru.omsu.imit.main.models.Employee;
import ru.omsu.imit.main.models.Organization;

import java.util.List;

public interface EmployeeMapper {
    @Insert("INSERT INTO employee (name, department_id) VALUES ( #{employee.name}, #{department.id} )")
    @Options(useGeneratedKeys = true, keyProperty = "employee.id")
    public Integer insert(@Param("department") Department department, @Param("employee") Employee employee);

    @Select("SELECT * FROM employee WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "scheduleList", column = "id", javaType = List.class, many = @Many(select = "ru.omsu.imit.main.mappers.ScheduleMapper.getByEmployee", fetchType = FetchType.LAZY) ),
            @Result(property = "department", column = "department_id", javaType = Department.class, one = @One(select = "ru.omsu.imit.main.mappers.DepartmentMapper.getByEmployee", fetchType = FetchType.LAZY) ) })
    public Employee getById(int id);

    @Select("SELECT * FROM employee WHERE department_id = #{department.id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "scheduleList", column = "id", javaType = List.class, many = @Many(select = "ru.omsu.imit.main.mappers.ScheduleMapper.getByEmployee", fetchType = FetchType.LAZY) ),
            @Result(property = "department", column = "department_id", javaType = Department.class, one = @One(select = "ru.omsu.imit.main.mappers.DepartmentMapper.getByEmployee", fetchType = FetchType.LAZY) ) })
    public List<Employee> getByDepartment(@Param("department") Department department);

    @Select("SELECT * FROM employee WHERE name = #{name}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "scheduleList", column = "id", javaType = List.class, many = @Many(select = "ru.omsu.imit.main.mappers.ScheduleMapper.getByEmployee", fetchType = FetchType.LAZY) ) ,
            @Result(property = "department", column = "department_id", javaType = Department.class, one = @One(select = "ru.omsu.imit.main.mappers.DepartmentMapper.getByEmployee", fetchType = FetchType.LAZY) ) })
    public List<Employee> getByName(String name);

    @Select("SELECT * FROM employee")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "scheduleList", column = "id", javaType = List.class, many = @Many(select = "ru.omsu.imit.main.mappers.ScheduleMapper.getByEmployee", fetchType = FetchType.LAZY) ) ,
            @Result(property = "department", column = "department_id", javaType = Department.class, one = @One(select = "ru.omsu.imit.main.mappers.DepartmentMapper.getByEmployee", fetchType = FetchType.LAZY) ) })
    public List<Employee> getAllLazy();

    @Delete("DELETE FROM employee")
    public void deleteAll();

    @Delete("DELETE FROM employee WHERE id = #{employee.id}")
    public void delete(@Param("employee") Employee employee);

    @Update("UPDATE employee SET name = #{name} WHERE id = #{employee.id} ")
    public void changeName(@Param("employee") Employee employee, @Param("name") String name);
}
