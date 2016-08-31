package com.hware.util;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;

import java.sql.Types;

/**
 * Created by Administrator on 14-4-15.
 */
public class ZJCMySQLDialect extends MySQLDialect {
    public ZJCMySQLDialect() {
        super();
        /*registerHibernateType(Types.DECIMAL, Hibernate.BIG_DECIMAL.getName());
        registerHibernateType(-1, Hibernate.STRING.getName());*/
    }

}