package com.hware.core.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.util.List;
import java.util.Map;





public class GenericDAO<T>
        implements IGenericDAO<T>
{
    protected  Class<T> entityClass;

    @Autowired
    @Qualifier("genericEntityDao")
    private GenericEntityDao geDao;

    public Class<T> getEntityClass()
    {
        return this.entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public GenericEntityDao getGeDao() {
        return this.geDao;
    }

    public void setGeDao(GenericEntityDao geDao) {
        this.geDao = geDao;
    }

    public GenericDAO() {
        this.entityClass =
                ((Class)((java.lang.reflect.ParameterizedType)getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public GenericDAO(Class<T> type)
    {
        this.entityClass = type;
    }

    public List executeNativeQuery(String nnq, Object[] params, int begin, int max)
    {
        return this.geDao.executeNativeQuery(nnq, params, begin, max);
    }

    public int executeNativeSQL(String nnq)
    {
        return this.geDao.executeNativeSQL(nnq);
    }

    public int queryCount(String nnq)
    {
        return this.geDao.queryCount(nnq);
    }


    public List find(String query, Map params, int begin, int max)
    {
        return getGeDao()
                .find(this.entityClass, query, params, begin, max);
    }

    public void flush()
    {
        this.geDao.flush();
    }

    public T get(Serializable id)
    {
        return (T) getGeDao().get(this.entityClass, id);//.get(this.entityClass, id);
    }

    public T getBy(String propertyName, Object value)
    {
        return (T) getGeDao().getBy(this.entityClass, propertyName, value);
    }

    @Override
    public List<T> executeNativeQuery(String nnq, Object[] paramArrayOfObject, Class<T> clazz, int begin, int max) {
        return this.geDao.createNativeQuery(nnq, paramArrayOfObject, begin, max, clazz);
    }

    @Override
    public int batchUpdate(String paramString, Object[] paramArrayOfObject) {
        return this.geDao.batchUpdate(paramString, paramArrayOfObject);
    }

    public List<T> query(String query, Map params, int begin, int max)
    {
        return getGeDao().query(query, params, begin, max);
    }

    public void remove(Serializable id)
    {
        getGeDao().remove(this.entityClass, id);
    }

    public void save(Object newInstance)
    {
        getGeDao().save(newInstance);
    }

    public void update(Object transientObject)
    {
        getGeDao().update(transientObject);
    }

}