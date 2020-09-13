package ru.omsu.imit.main.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.main.models.Department;
import ru.omsu.imit.main.models.Organization;

import java.util.List;

public interface OrganizationMapper {
    @Insert("INSERT INTO organization (name) VALUES ( #{name} )")
    @Options(useGeneratedKeys = true)
    public Integer insert(Organization organization);

    @Select("SELECT * FROM organization WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "departmentList", column = "id", javaType = List.class, many = @Many(select = "ru.omsu.imit.main.mappers.DepartmentMapper.getByOrganization", fetchType = FetchType.LAZY) ) })
    public Organization getById(int id);

    @Select("SELECT * FROM organization WHERE id = #{department.getOrganization().getId()}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "departmentList", column = "id", javaType = List.class, many = @Many(select = "ru.omsu.imit.main.mappers.DepartmentMapper.getByOrganization", fetchType = FetchType.LAZY) ) })
    public Organization getByDepartment(@Param("department") Department department);

    @Select("SELECT * FROM organization")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "departmentList", column = "id", javaType = List.class, many = @Many(select = "ru.omsu.imit.main.mappers.DepartmentMapper.getByOrganization", fetchType = FetchType.LAZY) ) })
    public List<Organization> getAllLazy();

    @Delete("DELETE FROM organization")
    public void deleteAll();

    @Delete("DELETE FROM organization WHERE id = #{organization.id}")
    public void delete(@Param("organization") Organization organization);

    @Update("Update organization SET name = #{name} WHERE id = #{organization.id}")
    public void changeName(@Param("organization") Organization organization, @Param("name") String name);
}
