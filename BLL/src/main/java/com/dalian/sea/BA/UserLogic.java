package com.dalian.sea.BA;

import Core.Data.DBExecutor;
import Models.Filter;
import com.dalian.sea.Models.BA.User;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-10-12.
 */
public class UserLogic {

    private static UserLogic _item;
    public static UserLogic Instance() {
        if (_item == null) {
            _item = new UserLogic();
        }
        return _item;
    }

    public User UserLogin(String Account, String Password, int p_intIndex,StringBuilder p_sbResult) throws IllegalAccessException, InstantiationException {
        WeakReference<DBExecutor> m_WeakReference = new WeakReference<DBExecutor>(new DBExecutor(p_intIndex));
        List<Filter> m_lstFilter = new ArrayList<Filter>();
        m_lstFilter.add(Filter.Equal("Account", Account));
        User entity = m_WeakReference.get().GetClassByPara(User.class,m_lstFilter);
        if (entity != null && entity.getUserid() != null && entity.getUserid() != 0) {
            if (entity.getStatus() == 1) {
                if (Password.toLowerCase().equals(entity.getPassword().toLowerCase())) {
                    int LogOnCount = entity.getLogoncount()==null?1:(entity.getLogoncount())+1;
                    entity.setPreviousvisit(entity.getLastvisit()==null?new Date():entity.getLastvisit());
                    entity.setLastvisit(new Date());
                    entity.setLogoncount(LogOnCount);
                    entity.setOnline(1);
                    m_WeakReference.get().Update(entity);
                    p_sbResult.append("succeed");
                } else {
                    p_sbResult.append("error");
                }
            } else {
                p_sbResult.append("lock");
            }
            return entity;
        }
        p_sbResult.append("-1");
        m_WeakReference.get().DBClose();
        return null;
    }
}
