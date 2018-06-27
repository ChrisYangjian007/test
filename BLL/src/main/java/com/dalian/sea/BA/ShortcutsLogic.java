package com.dalian.sea.BA;

import Core.Data.DBExecutor;
import Extend.StringExtend;
import com.dalian.sea.Models.BA.Module;
import com.dalian.sea.Models.BA.Shortcuts;
import Models.Enum.CurrentDatabase;
import Models.Enum.ErrorInfo;
import Models.Filter;
import Models.PagingOptions;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-10-19.
 */
public class ShortcutsLogic {
    private static ShortcutsLogic _item;

    public static ShortcutsLogic Instance() {
        if (_item == null) {
            _item = new ShortcutsLogic();
        }
        return _item;
    }

    /// <summary>
    /// 获取首页快捷方式列表
    /// </summary>
    /// <param name="UserId">用户Id</param>
    public List<Module> getShortcutList(Integer UserId) {
        StringBuilder strSql = new StringBuilder();
        strSql.append("WHERE ");
        strSql.append("S.CreatedUserID = ? ");
        List<Filter> lstFilter = new ArrayList<Filter>();
        lstFilter.add(Filter.Equal("CreatedUserID", UserId));
        WeakReference<DBExecutor> mWeakReference = new WeakReference<DBExecutor>(new DBExecutor(CurrentDatabase.MainDB.getCode()));
        List<Module> lstModule = mWeakReference.get().GetClassListByPara(Module.class, String.format("SELECT * FROM BA_Module M RIGHT JOIN BA_Shortcuts S ON s.ModuleID = M.ModuleID %s ORDER BY M.ListIndex", strSql.toString()), PagingOptions.Default(), lstFilter);
        mWeakReference.get().DBClose();
        return lstModule;
    }

    public int submitForm(String moduleId, final String UserId) {
        WeakReference<DBExecutor> mWeakReference = new WeakReference<DBExecutor>(new DBExecutor(CurrentDatabase.MainDB.getCode()));
        mWeakReference.get().BeginTransaction();
        String[] array = StringExtend.Trim(moduleId.trim(),",").split(",");
        try {
            int m_intReturn = mWeakReference.get().Delete(Shortcuts.class,array);
            if (m_intReturn < 0) {
                mWeakReference.get().RollbackTransection();
                return ErrorInfo.DelObjectFailure.getCode();//删除出错
            }
            for(final String item : array) {
                if (item.length() > 0) {
                    Shortcuts entity = new Shortcuts(){{
                        setModuleID(Integer.parseInt(item));
                        setCreatedUserID(Integer.parseInt(UserId));
                    }};
                    m_intReturn = mWeakReference.get().Insert(array,true);
                    if (m_intReturn < 0)
                    {
                        mWeakReference.get().RollbackTransection();
                        return ErrorInfo.InsertObjectFailure.getCode();//创建时出错
                    }
                }
            }
            mWeakReference.get().CommitTransection();
            mWeakReference.get().DBClose();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            mWeakReference.get().RollbackTransection();
            mWeakReference.get().DBClose();
            return  ErrorInfo.SystemErrorFailure.getCode();
        }
    }
}
