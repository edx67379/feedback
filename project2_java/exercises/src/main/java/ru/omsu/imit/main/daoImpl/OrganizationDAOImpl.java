package ru.omsu.imit.main.daoImpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.main.dao.OrganizationDAO;
import ru.omsu.imit.main.exception.MyException;
import ru.omsu.imit.main.models.Department;
import ru.omsu.imit.main.models.Organization;
import ru.omsu.imit.main.utils.ErrorCode;

import java.util.List;

public class OrganizationDAOImpl extends BaseDAOImpl implements OrganizationDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationDAOImpl.class);

    @Override
    public Organization insert(Organization organization) {
        LOGGER.debug("DAO Insert Organization {}", organization);
        try (SqlSession sqlSession = getSession()) {
            try {
                getOrganizationMapper(sqlSession).insert(organization);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Organization {} {}", organization, ex);
                sqlSession.rollback();
                throw new MyException(ErrorCode.EMPTY_PARAM);
            }
            sqlSession.commit();
        }
        return organization;
    }

    @Override
    public Organization getById(int id) {
        try (SqlSession sqlSession = getSession()) {
            return getOrganizationMapper(sqlSession).getById(id);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Organization by Id {} {}", id, ex);
            throw new MyException(ErrorCode.ORGANIZATION_NOT_FOUND);
        }
    }

    @Override
    public Organization getByDepartment(Department department) {
        try (SqlSession sqlSession = getSession()) {
            return getOrganizationMapper(sqlSession).getByDepartment(department);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Organization by Department {} {}", department, ex);
            throw ex;
        }
    }

    @Override
    public List<Organization> getAllLazy() {
        try (SqlSession sqlSession = getSession()) {
            return getOrganizationMapper(sqlSession).getAllLazy();
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get All Lazy {}", ex);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All Organizations {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getOrganizationMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Organization {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(Organization organization) {
        try (SqlSession sqlSession = getSession()) {
            try {
                getOrganizationMapper(sqlSession).delete(organization);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Organization {} {}", organization, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void changeName(Organization organization, String name) {
        try (SqlSession sqlSession = getSession()) {
            try {
                getOrganizationMapper(sqlSession).changeName(organization, name);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't change Organization Name {} {} ", organization, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
