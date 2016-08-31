package com.hware.core.dao;

import com.hware.core.base.GenericDAO;
import com.hware.core.domain.Result;
import com.hware.core.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangguojing on 2016/7/30.
 */
@Repository("ResultDAO")
public class ResultDAO extends GenericDAO<Result> {
}
