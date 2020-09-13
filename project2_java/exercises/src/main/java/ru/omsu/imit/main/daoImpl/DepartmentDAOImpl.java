package ru.omsu.imit.main.daoImpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.main.dao.DepartmentDAO;
import ru.omsu.imit.main.exception.MyException;
import ru.omsu.imit.main.models.Department;
import ru.omsu.imit.main.models.Employee;
import ru.omsu.imit.main.models.Organization;
import ru.omsu.imit.main.utils.ErrorCode;

import java.util.List;

public class DepartmentDAOImpl extends BaseDAOImpl implements DepartmentDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDAOImpl.class);

    @Override
    public Department insert(Organization organization, Department department) {
        LOGGER.debug("DAO Insert Department {}", department);
        try (SqlSession sqlSession = getSession()) {
            try {
                getDepartmentMapper(sqlSession).insert(organization, department);
                organization.getDepartmentList().add(department);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Department {} {}", department, ex);
                sqlSession.rollback();
                throw new MyException(ErrorCode.EMPTY_PARAM);
            }
            sqlSession.commit();
        }
        return department;
    }

    @Override
    public Department getById(int id) {
        try (SqlSession sqlSession = getSession()) {
            return getDepartmentMapper(sqlSession).getById(id);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Department by Id {} {}", id, ex);
            throw new MyException(ErrorCode.DEPARTMENT_NOT_FOUND);
        }
    }

    @Override
    public List<Department> getByOrganization(Organization organization) {
        try (SqlSession sqlSession = getSession()) {
            return getDepartmentMapper(sqlSession).getByOrganization(organization);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Department by Organization {} {}", organization, ex);
            throw ex;
        }
    }

    @Override
    public Department getByEmployee(Employee employee) {
        try (SqlSession sqlSession = getSession()) {
            return getDepartmentMapper(sqlSession).getByEmployee(employee);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Department by Employee {} {}", employee, ex);
            throw ex;
        }
    }

    @Override
    public List<Department> getByName(String name) {
        try (SqlSession sqlSession = getSession()) {
            return getDepartmentMapper(sqlSession).getByName(name);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Department by Name {} {}", name, ex);
            throw ex;
        }
    }

    @Override
    public List<Department> getAllLazy() {
        try (SqlSession sqlSession = getSession()) {
            return getDepartmentMapper(sqlSession).getAllLazy();
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get All Lazy {}", ex);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All Departments {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getDepartmentMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Departments {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(Department department) {
        try (SqlSession sqlSession = getSession()) {
            try {
                getDepartmentMapper(sqlSession).delete(department);
                department.getOrganization().getDepartmentList().remove(department);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Department {} {}", department, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void changeName(Department department, String name) {
        try (SqlSession sqlSession = getSession()) {
            try {
                getDepartmentMapper(sqlSession).changeName(department, name);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't change Department Name {} {} ", department, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
