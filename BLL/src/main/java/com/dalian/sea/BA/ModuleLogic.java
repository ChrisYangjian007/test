package com.dalian.sea.BA;

import com.dalian.sea.Base.BaseLogic;
import com.dalian.sea.Base.ManageLogic;
import Core.Data.DBExecutor;
import Models.Enum.CurrentDatabase;
import Models.Filter;
import com.dalian.sea.Models.BA.Module;
import Models.PagingOptions;
import Models.SortBy;
import Utils.CommonHelper;
import com.dalian.sea.WebClass.ManageUser;

import javax.servlet.http.HttpServletRequest;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-10-17.
 */
public class ModuleLogic {
    private static ModuleLogic _item;

    public static ModuleLogic Instance() {
        if (_item == null) {
            _item = new ModuleLogic();
        }
        return _item;
    }

    /*public String getModuleList( HttpServletRequest request) {
        StringBuilder strSql = new StringBuilder();
        ManageUser nowLoginUser = ManageLogic.Instance().Current(request);
        List<Filter> lstFilter = new ArrayList<Filter>();
        if (nowLoginUser == null || nowLoginUser.ISys == 0) {
//            int ModuleID = 0;
//            if (nowLoginUser.UserAuthorityType == 11)
//            {
//                ModuleID = 2401;
//            }
//            else if (nowLoginUser.UserAuthorityType == 12)
//            {
//                ModuleID = 2402;
//            }
//            else
//            {
//                ModuleID = 2403;
//            }
            List<SortBy> lstSortBy = new ArrayList<SortBy>();
//            m_lstFilter.add(Filter.And(Filter.Or(Filter.Equal("ParentID", 0), Filter.Equal("ID", ModuleID)), Filter.Equal("Status", 1)));
            //var listData = BaseLogic.Instance.GetListInfo<Module>(m_lstFilter, (int)Models.Enum.CurrentDatabase.MainDB, "ModuleID,Category,Code,CName,Icon,Location,Target,ParentID,M.ListIndex");
            List<Module> listData = BaseLogic.Instance().getListByPara(Module.class, lstFilter, CurrentDatabase.MainDB.getCode(), "ModuleID,Category,Code,CName,Icon,Location,Target,ParentID", lstSortBy);
            //var listData = Module.Data.Where(u => (u.ParentID == ModuleID || u.ID == ModuleID) && u.Status == 1).ToList();
            return CommonHelper.ModelToJson(listData);
            //strSql.Append(string.Format(ModuleSQL.Get_ModuleList_SQL, ManageLogic.Instance.Current().OID.Replace(",", "','")));
        } else {
            strSql.append("SELECT ModuleID,Category,Code,CName,Icon,Location,Target,ParentID,M.ListIndex FROM BA_Module M Where M.Status=1 ");
        }
        strSql.append(" ORDER BY M.ListIndex ASC ");
        WeakReference<DBExecutor> mWeakReference = new WeakReference<DBExecutor>(new DBExecutor(CurrentDatabase.MainDB.getCode()));
        List<Module> m_lstObject = mWeakReference.get().GetClassListByPara(Module.class, strSql.toString(), PagingOptions.Default(), lstFilter);
        mWeakReference.get().DBClose();
        if (m_lstObject == null || m_lstObject.size() == 0) {
            return "";
        }
        return CommonHelper.ModelToJson(m_lstObject);
    }
    public List<Module> getModuleListByUser(ManageUser manageUser) {
        StringBuilder strSql = new StringBuilder();
        if (manageUser == null) {
            return new ArrayList<Module>();
        }
        if (manageUser.ISys == 0) {
            strSql.append(String.format("SELECT DISTINCT M.ModuleID,M.Category,M.MobileLocation,M.MobileIcon,M.Code,M.CName,M.MobileShow,M.Icon,M.Location,M.Target,M.ParentID,M.ListIndex FROM BA_Module M INNER JOIN BA_ModulePermission MP ON M.ModuleId = MP.ModuleId WHERE M.Status=1 and MP.Status=1 and ObjectID IN ('%s')", manageUser.OID.replace(",", "','")));
        } else {
            strSql.append("SELECT ModuleID,Category,Code,CName,Icon,Location,Target,MobileLocation,MobileShow,MobileIcon,ParentID,M.ListIndex FROM BA_Module M Where M.Status=1 ");
        }
        strSql.append(" ORDER BY M.ListIndex ASC ");
        List<Filter> lstFilter = new ArrayList<Filter>();
        WeakReference<DBExecutor> mWeakReference = new WeakReference<DBExecutor>(new DBExecutor(CurrentDatabase.MainDB.getCode()));
        List<Module> lstModule = mWeakReference.get().GetClassListByPara(Module.class, strSql.toString(), PagingOptions.Default(), lstFilter);
        mWeakReference.get().DBClose();
        return lstModule;
    }*/
}
