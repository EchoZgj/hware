package com.hware.core.service.impl;

import com.hware.core.base.GenericDAO;
import com.hware.core.base.IGenericDAO;
import com.hware.core.domain.Result;
import com.hware.core.domain.Testdata;
import com.hware.core.domain.User;
import com.hware.core.service.ITestDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/2.
 */
@Service
@Transactional
public class TestDataService implements ITestDataService {

    @Resource(name="testDataDAO")
    private GenericDAO<Testdata> testDataDAO;

    @Resource(name="userDAO")
    private IGenericDAO<User> userDAO;


    @Override
    public void save(Testdata result) {
        testDataDAO.save(result);
    }

    @Override
    public void delete(Long id) {
        testDataDAO.remove(id);
    }

    @Override
    public void update(Testdata result) {
        testDataDAO.update(result);
    }

    @Override
    public Testdata getResultById(Long id) {
        return testDataDAO.get(id);
    }

    @Override
    public Testdata getBy(String propertyName, Object value) {
        return testDataDAO.getBy(propertyName,value);
    }

    @Override
    public void keep(User user, Testdata testdata) {

    }

    @Override
    public List<Testdata> query(String paramString, Map paramMap, int paramInt1, int paramInt2) {
        return testDataDAO.query(paramString, paramMap, paramInt1, paramInt2);
    }
}
