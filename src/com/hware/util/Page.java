package com.hware.util;

import java.util.List;

/**
 * Created by Leon on 2015/12/8 0008.
 * 分页组件模型
 */
public class Page<T> {

    private int totalPage;
    private int pageNo;
    private int pageSize;
    private List<PropertyFilter> filters;
    private List<T> list;

    public int getStartNo() {
        return (pageNo - 1) * pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public Page(List<PropertyFilter> filters, int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.filters = filters;
    }

    public int getFirst(){
        return 0;
    }

    public int getLast(){
        return totalPage;
    }

    public boolean getHasNext(){
        if(pageNo < totalPage){
            return true;
        }
        return false;
    }

    public boolean getHasPre(){
        if(pageNo > 0){
            return true;
        }
        return false;
    }

    public int getNext(){
        int ne = pageNo ++;
        if(ne >= totalPage){
            return totalPage;
        }
        return ne;
    }

    public int getPer(){
       int pre = pageNo --;
        if(pre <= 0){
            return 1;
        }
        return pre;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setTotalPage(int totalCount) {
        this.totalPage = totalCount / pageSize;
        if(totalCount % pageSize > 0){
            this.totalPage ++;
        }
    }

    public List<PropertyFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<PropertyFilter> filters) {
        this.filters = filters;
    }

}
