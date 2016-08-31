package com.hware.util;

import java.util.List;

/**
 * Created by Leon on 2015/12/8 0008.
 */
public class PropertyFilter {
    private String key;
    private Object[] values;
    private LINK link;

    public PropertyFilter(String key, LINK link, Object... values) {
        this.key = key;
        this.values = values;
        this.link = link;
    }

    public enum LINK{
        EQ,LIKE,BETWEEN,OR
    }

    public LINK getLink() {
        return link;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public static String analysisFilters(List<PropertyFilter> filters){
        StringBuffer stringBuffer = new StringBuffer();
        for(PropertyFilter filter : filters){
            String key = filter.getKey();
            Object[] values = filter.getValues();
            PropertyFilter.LINK link = filter.getLink();
            if(link.equals(PropertyFilter.LINK.EQ)){
                stringBuffer.append(" AND " + key + " = '" + values[0].toString() + "' ");
            }else  if(link.equals(PropertyFilter.LINK.LIKE)){
                stringBuffer.append(" AND " + key + " LIKE '%" + values[0].toString() + "%' ");
            }else if(link.equals(PropertyFilter.LINK.BETWEEN)){
                stringBuffer.append(" AND " + key + " BETWEEN '"  + values[0] + "' AND '" + values[1] + "' ");
            }else if(link.equals(PropertyFilter.LINK.OR)){
                if(values != null && values.length > 0){
                    stringBuffer.append(" AND ( ");
                    for(int i = 0 ; i < values.length; i ++){
                        if(i >= (values.length - 1)){
                            stringBuffer.append(key + " = '" + values[i].toString() + "' ");
                        }else{
                            stringBuffer.append(key + " = '" + values[i].toString() + "' OR ");
                        }
                    }
                    stringBuffer.append(" ) ");
                }
            }
        }
        return stringBuffer.toString();
    }

}
