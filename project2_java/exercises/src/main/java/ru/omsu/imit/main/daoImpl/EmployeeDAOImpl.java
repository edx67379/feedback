package ru.omsu.imit.main.daoImpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.main.dao.EmployeeDAO;
import ru.omsu.imit.main.exception.MyException;
import ru.omsu.imit.main.models.Department;
import ru.omsu.imit.main.models.Employee;
import ru.omsu.imit.main.utils.ErrorCode;

import java.util.List;

public class EmployeeDAOImpl extends BaseDAOImpl implements EmployeeDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDAOImpl.class);

    @Override
    public Employee insert(Department department, Employee employee) {
        LOGGER.debug("DAO Insert Employee {}", employee);
        try (SqlSession sqlSession = getSession()) {
            try {
                getEmployeeMapper(sqlSession).insert(department, employee);
                department.getEmployeeList().add(employee);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Employee {} {}", employee, ex);
                sqlSession.rollback();
                throw new MyException(ErrorCode.EMPTY_PARAM);
            }
            sqlSession.commit();
        }
        return employee;
    }

    @Override
    public Employee getById(int id) {
        try (SqlSession sqlSession = getSession()) {
            return getEmployeeMapper(sqlSession).getById(id);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Employee by Id {} {}", id, ex);
            throw new MyException(ErrorCode.EMPLOYEE_NOT_FOUND);
        }
    }

    @Override
    public List<Employee> getByDepartment(Department department) {
        try (SqlSession sqlSession = getSession()) {
            return getEmployeeMapper(sqlSession).getByDepartment(department);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Employee by Department {} {}", department, ex);
            throw ex;
        }
    }

    @Override
    public List<Employee> getByName(String name) {
        try (SqlSession sqlSession = getSession()) {
            return getEmployeeMapper(sqlSession).getByName(name);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Employee by Name {} {}", name, ex);
            throw ex;
        }
    }

    @Override
    public List<Employee> getAllLazy() {
        try (SqlSession sqlSession = getSession()) {
            return getEmployeeMapper(sqlSession).getAllLazy();
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get All Lazy {}", ex);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All Employees {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getEmployeeMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Employees {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(Employee employee) {
        try (SqlSession sqlSession = getSession()) {
            try {
                getEmployeeMapper(sqlSession).delete(employee);
                employee.getDepartment().getEmployeeList().remove(employee);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Employee {} {}", employee, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void changeName(Employee employee, String name) {
        try (SqlSession sqlSession = getSession()) {
            try {
                getEmployeeMapper(sqlSession).changeName(employee, name);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't change Employee Name {} {} ", employee, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
