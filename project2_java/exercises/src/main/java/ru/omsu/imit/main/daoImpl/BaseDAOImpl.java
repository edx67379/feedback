package ru.omsu.imit.main.daoImpl;

import org.apache.ibatis.session.SqlSession;
import ru.omsu.imit.main.mappers.DepartmentMapper;
import ru.omsu.imit.main.mappers.EmployeeMapper;
import ru.omsu.imit.main.mappers.OrganizationMapper;
import ru.omsu.imit.main.mappers.ScheduleMapper;
import ru.omsu.imit.main.utils.MyBatisUtils;

public class BaseDAOImpl {
    protected SqlSession getSession() {
        return MyBatisUtils.getSqlSessionFactory().openSession();
    }

    protected OrganizationMapper getOrganizationMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(OrganizationMapper.class);
    }

    protected DepartmentMapper getDepartmentMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(DepartmentMapper.class);
    }

    protected EmployeeMapper getEmployeeMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(EmployeeMapper.class);
    }

    protected ScheduleMapper getScheduleMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(ScheduleMapper.class);
    }
}
