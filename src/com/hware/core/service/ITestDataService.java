package com.hware.core.service;

import com.hware.core.domain.Result;
import com.hware.core.domain.Testdata;
import com.hware.core.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/2.
 */
public interface ITestDataService {
    public void save(Testdata result);
    public void delete(Long id);
    public void update(Testdata result);
    public Testdata getResultById(Long id);

    Testdata getBy(String propertyName, Object value);

    void keep(User user,Testdata testdata);

    List<Testdata> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}
