package com.hware.core.base;

import com.easyjf.util.CommUtil;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class GenericEntityDao extends HibernateDaoSupport
{
    public Object get(Class clazz, Serializable id)
    {
        return  currentSession().get(clazz, id);
    }

    public List<Object> find( Class clazz, final String queryStr, final Map params, final int begin, final int max)
    {
    /*final Class claz = clazz;
    List ret = (List)getJpaTemplate().execute( new JpaCallback()
    {
    	*//*private Class claz;
    	private String queryStr;
    	private Map params;
    	private int begin;
    	private int max;
    	public JpaCallback(Class clazz, String queryStr, Map params, int begin, int max){
    		this.claz =claz;
    		this.queryStr = queryStr;
    		this.params = params;
    		this.begin =begin;
    		this.max =max;
    	}*//*
      public Object doInJpa(EntityManager em) throws PersistenceException {
    	
        String clazzName = claz.getName();
        StringBuffer sb = new StringBuffer("select obj from ");
        sb.append(clazzName).append(" obj").append(" where ")
          .append(queryStr);
        Query query = em.createQuery(sb.toString());
        for (Iterator localIterator = params.keySet().iterator(); localIterator.hasNext(); ) { Object key = localIterator.next();
          query.setParameter(key.toString(), params.get(key));
        }
        if ((begin >= 0) && (max > 0)) {
          query.setFirstResult(begin);
          query.setMaxResults(max);
        }
        query.setHint("org.hibernate.cacheable", Boolean.valueOf(true));
        return query.getResultList();
      }
    });
    if ((ret != null) && (ret.size() >= 0)) {
      return ret;
    }
    return new ArrayList();*/

        String clazzName = clazz.getName();
        StringBuffer sb = new StringBuffer("select obj from ");
        sb.append(clazzName).append(" obj").append(" where ")
                .append(queryStr);
        Query query = currentSession().createQuery(sb.toString());
        for (Iterator localIterator = params.keySet().iterator(); localIterator.hasNext(); ) { Object key = localIterator.next();
            query.setParameter(key.toString(), params.get(key));
        }
        if ((begin >= 0) && (max > 0)) {
            query.setFirstResult(begin);
            query.setMaxResults(max);
        }
        return query.list();

    }

    public List<Object> findByNativeSql( Class clazz,final String sql, final String queryStr, final Map params, final int begin, final int max)
    {
        final Class claz = clazz;
        String clazzName = claz.getName();
        StringBuffer sb = new StringBuffer(sql+" ");
        sb.append(clazzName).append(" obj").append(" where ")
                .append(queryStr);
        // Query query = em.createQuery(sb.toString());
        Query query = currentSession().createSQLQuery(sb.toString());
        for (Iterator localIterator = params.keySet().iterator(); localIterator.hasNext(); ) { Object key = localIterator.next();
            query.setParameter(key.toString(), params.get(key));
        }
        if ((begin >= 0) && (max > 0)) {
            query.setFirstResult(begin);
            query.setMaxResults(max);
        }
        return query.list();
    }

    public List query( final String queryStr, final Map params, final int begin, final int max)
    {

        Query query =currentSession().createQuery(queryStr);
        if ((params != null) && (params.size() > 0)) {
            for (Iterator localIterator = params.keySet().iterator(); localIterator.hasNext(); ) { Object key = localIterator.next();
                query.setParameter(key.toString(), params.get(key));
            }
        }
        if ((begin >= 0) && (max > 0)) {
            query.setFirstResult(begin);
            query.setMaxResults(max);
        }
        return query.list();

    }

    public void remove(Class clazz, Serializable id)
            throws RuntimeException
    {
        Object object = get(clazz, id);
        if (object != null)
            try {
                currentSession().delete(object);
            } catch (Exception e) {
                throw new RuntimeException();
            }
    }

    public void save(Object instance)
    {
        currentSession().save(instance);
    }

    public Object getBy(Class clazz,final String propertyName,final Object value)
    {
        final Class claz = clazz;


        String clazzName = claz.getName();
        StringBuffer sb = new StringBuffer("select obj from ");
        sb.append(clazzName).append(" obj");
        Query query = null;
        if ((propertyName != null) && (value != null)) {
            sb.append(" where obj.").append(propertyName)
                    .append(" = :value");
            query = currentSession().createQuery(sb.toString()).setParameter(
                    "value", value);
        } else {
            query = currentSession().createQuery(sb.toString());
        }
        return query.uniqueResult();

    }


    /*public Query getNativeQuery(){
      return getJpaTemplate().find()
    }*/
    public void update(Object instance)
    {
        currentSession().update(instance);
    }



    public List executeNativeQuery(final String nnq, final Map params, final int begin, final int max)
    {

        Query query = currentSession().createSQLQuery(nnq);
        int parameterIndex = 1;
        if (params != null) {
            Iterator its = params.keySet().iterator();
            while (its.hasNext()) {
                query.setParameter(CommUtil.null2String(its.next()),
                        params.get(its.next()));
            }
        }
        if ((begin >= 0) && (max > 0)) {
            query.setFirstResult(begin);
            query.setMaxResults(max);
        }

        return query.list();

    }

    public List executeNativeQuery(final String nnq, final Object[] params, final int begin, final int max)
    {

        Query query = currentSession().createSQLQuery(nnq);
        int parameterIndex = 1;
        if ((params != null) && (params.length > 0)) {
            for (Object obj : params) {
                query.setParameter(parameterIndex++, obj);
            }
        }
        if ((begin >= 0) && (max > 0)) {
            query.setFirstResult(begin);
            query.setMaxResults(max);
        }

        return query.list();

    }

    public <T>List<T> createNativeQuery(final String nnq, final Object[] params, final int begin, final int max, final Class<T> clazz){

        Query query = currentSession().createSQLQuery(nnq);
        int paramterIndex = 1;
        if (params != null && params.length > 0) {
            for (Object obj : params) {
                query.setParameter(paramterIndex++, obj);
            }
        }
        if ((begin >= 0) && (max > 0)) {
            query.setFirstResult(begin);
            query.setMaxResults(max);
        }
        return query.list();

    }

    public int queryCount(final String nnq){

        Query query = currentSession().createSQLQuery(nnq);
        return CommUtil.null2Int(query.list().get(0));

    }


    public int executeNativeSQL(final String nnq)
    {
        Query query = currentSession().createSQLQuery(nnq);
        return Integer.valueOf(query.executeUpdate());

    }

    public int batchUpdate(final String jpql, final Object[] params) {

        Query query = currentSession().createQuery(jpql);
        int parameterIndex = 1;
        if ((params != null) && (params.length > 0)) {
            for (Object obj : params) {
                query.setParameter(parameterIndex++, obj);
            }
        }
        return Integer.valueOf(query.executeUpdate());

    }

    public void flush() {
        currentSession().flush();
    }
}