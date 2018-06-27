package com.dalian.sea.BA;

import Core.Data.DBExecutor;
import com.dalian.sea.Models.BA.Address;
import Models.Enum.CurrentDatabase;
import Models.Filter;
import Models.PagingOptions;
import com.dalian.sea.WebClass.ManageUser;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-10-20.
 */
public class AddressLogic {
    private static AddressLogic _item;
    public static AddressLogic Instance() {
        if (_item == null) {
            _item = new AddressLogic();
        }
        return _item;
    }

    /// <summary>
    /// 获取数据字典明细列表
    /// </summary>
    /// <param name="Code">分类编码</param>
    public void updateAddressPrimary(int AddressID, ManageUser manageUser) {
        StringBuilder strSql = new StringBuilder();
        List<Filter> lstFilter = new ArrayList<Filter>();
        strSql.append("Update BA_Address set IsPrimary=0 Where Status=1 and IsPrimary=1 ");
        if (manageUser != null && manageUser.ISys == 0) {
            strSql.append(" And CompanyID IN (" + String.valueOf(manageUser.CID) + ") ");
        } else {
            strSql.append(" And CompanyID IN (select CompanyID from BA_Address where AddressID=?)");
        }
        if (AddressID != 0) {
            strSql.append(" And AddressID <> ? ");
            lstFilter.add(Filter.Equal("AddressID", AddressID));
        }
        WeakReference<DBExecutor> mWeakReference = new WeakReference<DBExecutor>(new DBExecutor(CurrentDatabase.MainDB.getCode()));
        mWeakReference.get().Update(strSql.toString(), lstFilter);
        mWeakReference.get().DBClose();
    }

    /// <summary>
    /// 获取数据字典明细列表
    /// </summary>
    /// <param name="Code">分类编码</param>
    public List<Address> GetAddressList(String Code, ManageUser manageUser) {
        StringBuilder strSql = new StringBuilder();
        List<Filter> lstFilter = new ArrayList<Filter>();
        strSql.append("select AddressID,(AreaName +' '+AddressName) as AddressName from BA_Address M Where M.Status=1 ");
        if (manageUser != null && manageUser.ISys == 0) {
            strSql.append(" And CompanyID IN (" + String.valueOf(manageUser.CID) + ") ");
        }
        strSql.append(" ORDER BY M.IsPrimary Desc ");
        WeakReference<DBExecutor> mWeakReference = new WeakReference<DBExecutor>(new DBExecutor(CurrentDatabase.MainDB.getCode()));
        List<Address> lstAddress = mWeakReference.get().GetClassListByPara(Address.class, strSql.toString(), PagingOptions.Default(), lstFilter);
        mWeakReference.get().DBClose();
        return lstAddress;
    }
}
