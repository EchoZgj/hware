package com.hware.core.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;




public interface IGenericDAO<T>
{
    T get(Serializable paramSerializable);

    void save(T paramT);

    void remove(Serializable paramSerializable);

    void update(T paramT);

    T getBy(String paramString, Object paramObject);

    List<T> executeNativeQuery(String nnq, Object[] paramArrayOfObject, Class<T> clazz, int begin, int max);

    public abstract int batchUpdate(String paramString, Object[] paramArrayOfObject);


    List<T> find(String paramString, Map paramMap, int paramInt1, int paramInt2);

    List<T> query(String paramString, Map paramMap, int paramInt1, int paramInt2);

    List executeNativeQuery(String paramString, Object[] paramArrayOfObject, int paramInt1, int paramInt2);

    int executeNativeSQL(String paramString);

    int queryCount(String nnq);

    void flush();
}