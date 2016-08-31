package com.hware.core.service;

import com.hware.core.domain.Result;
import com.hware.core.domain.User;

/**
 * Created by zhangguojing on 2016/7/30.
 */
public interface IResultService {

    public void save(Result result);
    public void delete(Long id);
    public void update(Result result);
    public Result getResultById(Long id);
}
