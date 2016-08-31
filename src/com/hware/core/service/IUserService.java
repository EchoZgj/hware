package com.hware.core.service;

import com.hware.core.domain.User;
import com.hware.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface IUserService {
    public  void save(User paramAccessory);

    public  void delete(Long paramLong);

    public  void update(User paramAccessory);

    public  User getObjById(Long paramLong);

    public User getObjByProperty(String propertyName, String value);

    User getBy(String propertyName, Object value);

    List<User> query(String paramString, Map paramMap, int paramInt1, int paramInt2);

    public PageBean getFenDaList(int pageSize, int page, Map<String, String> query);

}
