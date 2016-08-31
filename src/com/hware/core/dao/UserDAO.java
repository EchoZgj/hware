package com.hware.core.dao;

import com.hware.core.base.GenericDAO;
import com.hware.core.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/7/28.
 */
@Repository("userDAO")
public class UserDAO extends GenericDAO<User> {
}
