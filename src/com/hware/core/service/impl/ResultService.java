package com.hware.core.service.impl;

import com.hware.core.base.GenericDAO;
import com.hware.core.domain.Result;
import com.hware.core.domain.User;
import com.hware.core.service.IResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhangguojing on 2016/7/30.
 */
@Service
@Transactional
public class ResultService implements IResultService{

    @Resource(name="ResultDAO")
    private GenericDAO<Result> resultDao;

    @Override
    public void save(Result result) {
        resultDao.save(result);
    }

    @Override
    public void delete(Long id) {
        resultDao.remove(id);
    }

    @Override
    public void update(Result result) {
        resultDao.update(result);
    }

    @Override
    public Result getResultById(Long id) {
        return resultDao.get(id);
    }



}
