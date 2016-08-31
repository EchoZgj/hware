package com.hware.core.service;

import com.hware.core.domain.Measure;

/**
 * Created by zhangguojing on 2016/7/30.
 */
public interface IMeasureService {

    public void save(Measure measure);
    public void delete(Long id);
    public void update(Measure measure);
    public Measure getMeasureById(Long id);
}
