package com.hware.core.service.impl;

import com.hware.core.base.GenericDAO;
import com.hware.core.domain.Measure;
import com.hware.core.service.IMeasureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhangguojing on 2016/7/30.
 */
@Service
@Transactional
public class MeasureService implements IMeasureService{

    @Resource
    private GenericDAO<Measure> measureDAO;

    @Override
    public void save(Measure measure) {
        measureDAO.save(measure);
    }

    @Override
    public void delete(Long id) {
        measureDAO.remove(id);
    }

    @Override
    public void update(Measure measure) {
        measureDAO.update(measure);
    }

    @Override
    public Measure getMeasureById(Long id) {
        return measureDAO.get(id);
    }
}
