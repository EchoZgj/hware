package com.hware.core.service.impl;

import com.hware.core.base.IGenericDAO;
import com.hware.core.dao.UserDAO;
import com.hware.core.domain.User;
import com.hware.core.service.IUserService;
import com.hware.tools.CommUtil;
import com.hware.util.PageBean;
import com.hware.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/28.
 */
@Service
@Transactional
public class UserService implements IUserService {

    @Resource(name="userDAO")
    private IGenericDAO<User> userDAO;

    @Override
    public void save(User paramAccessory) {
        userDAO.save(paramAccessory);
    }

    @Override
    public void delete(Long paramLong) {
        userDAO.remove(paramLong);
    }

    @Override
    public void update(User paramAccessory) {
        userDAO.update(paramAccessory);
    }

    @Override
    public User getObjById(Long paramLong) {
        return userDAO.get(paramLong);
    }

    @Override
    public User getObjByProperty(String propertyName, String value) {
        return userDAO.getBy(propertyName, value);
    }

    @Override
    public User getBy(String propertyName, Object value) {
        return userDAO.getBy(propertyName, value);
    }

    @Override
    public List<User> query(String paramString, Map paramMap, int paramInt1, int paramInt2) {
        return userDAO.query(paramString, paramMap, paramInt1, paramInt2);
    }

    @Override
    public PageBean getFenDaList(int pageSize, int page, Map<String, String> query) {
        Long userNo=null;
        String userName=null;

        String hql = "from User obj where 1=1 ";
        if(query != null && query.size() >0 ){
            if(query.get("id")!=null && !query.get("id").equals("")){
                userNo=Long.valueOf(query.get("id"));
                hql+=" and obj.userNo = "+userNo;
            }else if(query.get("userName")!=null && !query.get("userName").equals("")){
                userName=query.get("userName");
                hql+=" and obj.userName like '%"+userName+"%'";
            }
        }
        hql +=" order by obj.measureDate desc";
        System.out.println("hql = "+hql);
        int allRow = this.getAllRowCount(hql);
        int totalPage = PageBean.countTotalPage(pageSize, allRow);
        final int offset = PageBean.countOffset(pageSize, page);
        final int length = pageSize;
        final int currentPage = PageBean.countCurrentPage(page);
        List list = this.queryForPage(hql, offset, length);
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        pageBean.init();
        return pageBean;
    }

    public int getAllRowCount(String hql) {
        /*hql = hql.substring(0,hql.indexOf("order"));
        System.out.println(hql);*/
        List list1 =userDAO.query("select count(obj) "+hql,null,-1,-1);
        if(list1==null){
            return 0;
        }else {
            int num = CommUtil.null2Int(list1.get(0));
            return num;
        }
    }

    public List queryForPage(final String hql,final int offset,final int length){
        List list1 =userDAO.query(hql, null, offset, length);
        return list1;
    }
}
