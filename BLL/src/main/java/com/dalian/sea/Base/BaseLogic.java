package com.dalian.sea.Base;

import Core.Data.DBExecutor;
import Extend.StringExtend;
import Models.Enum.ErrorInfo;
import Models.Filter;
import Models.JqGridParam;
import Models.PagingOptions;
import Models.SortBy;
import Utils.CommonHelper;
import Utils.ReflectionUtil;
import com.dalian.sea.WebClass.ManageUser;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Administrator on 2017-07-30.
 */
public class BaseLogic {

    private static BaseLogic _item;

    public static BaseLogic Instance() {
        if (_item == null) {
            _item = new BaseLogic();
        }
        return _item;
    }

    public <T> T getInfoByID(Class<T> t, final int p_intID, int p_intIndex) {
        Filter m_Filter = Filter.Equal("ID", p_intID);
        List<Filter> m_lstFilter = new ArrayList<Filter>();
        m_lstFilter.add(m_Filter);
        return getInfoByPara(t, m_lstFilter, p_intIndex, new ArrayList<SortBy>());
    }

    public <T> T getInfoByID(Class<T> t, final String p_strID, int p_intIndex) {
        Filter m_Filter = Filter.Equal("ID", p_strID);
        List<Filter> m_lstFilter = new ArrayList<Filter>();
        m_lstFilter.add(m_Filter);
        return getInfoByPara(t, m_lstFilter, p_intIndex, new ArrayList<SortBy>());
    }

    public <T> T getInfoByPara(Class<T> t, List<Filter> p_lstFilter, int p_intIndex, List<SortBy> p_SortBy) {
        WeakReference<DBExecutor> m_WeakReference = new WeakReference<DBExecutor>(new DBExecutor(p_intIndex));
        PagingOptions m_PagingOptions = PagingOptions.Info();
        m_PagingOptions.SortBy = p_SortBy;
        T m_T = m_WeakReference.get().GetClassByPara(t, m_PagingOptions, p_lstFilter);
        m_WeakReference.get().DBClose();
        return m_T;
    }

    public <T> List<T> getListByPara(Class<T> t, List<Filter> p_lstFilter, int p_intIndex, String selectColumn, List<SortBy> pSortBy) {
        PagingOptions m_PagingOptions = PagingOptions.Default();
        if (!StringExtend.Empty(selectColumn)) {
            m_PagingOptions.SelectColumn = selectColumn;
        }
        if (pSortBy != null) {
            m_PagingOptions.SortBy = pSortBy;
        }
        WeakReference<DBExecutor> m_WeakReference = new WeakReference<DBExecutor>(new DBExecutor(p_intIndex));
        List<T> m_lstT = m_WeakReference.get().GetClassListByPara(t, m_PagingOptions, p_lstFilter);
        m_WeakReference.get().DBClose();
        return m_lstT;
    }

    public <T> List<T> getListByPara(Class<T> t, String p_strSql, List<Filter> p_lstFilter, int p_intIndex) {
        PagingOptions m_PagingOptions = PagingOptions.Default();
        WeakReference<DBExecutor> m_WeakReference = new WeakReference<DBExecutor>(new DBExecutor(p_intIndex));
        List<T> m_lstT = m_WeakReference.get().GetClassListByPara(t, p_strSql, m_PagingOptions, p_lstFilter);
        m_WeakReference.get().DBClose();
        return m_lstT;
    }

    public <T> List<T> getListByPara(Class<T> t, List<Filter> p_lstFilter, int p_intIndex, String selectColumn, PagingOptions p_Options) {
        WeakReference<DBExecutor> mWeakReference = new WeakReference<DBExecutor>(new DBExecutor(p_intIndex));
        if (!StringExtend.Empty(selectColumn)) {
            p_Options.SelectColumn = selectColumn;
        }
        List<T> m_lstT = mWeakReference.get().GetClassListByPara(t, p_Options, p_lstFilter);
        mWeakReference.get().DBClose();
        return m_lstT;
    }

    /// <summary>
    /// 插入数据
    /// </summary>
    public <T> String getMaxIndex(Class<T> t ,String mStrParaName , int index ) {
        WeakReference<DBExecutor> mWeakReference = new WeakReference<DBExecutor>(new DBExecutor(index));
        int intReturn = mWeakReference.get().FindMax(t, mStrParaName);
        mWeakReference.get().DBClose();
        return String.valueOf(intReturn);
    }

    public <T> List<T> getListByPara(Class<T> t, List<Filter> p_lstFilter, JqGridParam jqgridparam, int p_intIndex, String selectColumn) {
        PagingOptions m_PagingOptions = PagingOptions.Default();
        m_PagingOptions.PageSize = jqgridparam.getRows();
        m_PagingOptions.StartNumber = (jqgridparam.getPage() - 1) * m_PagingOptions.PageSize;
        m_PagingOptions.FetchTotalRecordCount = true;

        if (!StringExtend.Empty(selectColumn)) {
            m_PagingOptions.SelectColumn = selectColumn;
        }
        if (!StringExtend.Empty(jqgridparam.getSidx()) && !StringExtend.Empty(jqgridparam.getSord())) {
            m_PagingOptions.SortBy.add(new SortBy(jqgridparam.getSidx(), jqgridparam.getSord().toLowerCase() != "desc"));
        }

        WeakReference<DBExecutor> m_WeakReference = new WeakReference<DBExecutor>(new DBExecutor(p_intIndex));
        List<T> m_lstT = m_WeakReference.get().GetClassListByPara(t, m_PagingOptions, p_lstFilter);
        jqgridparam.setRecords(m_PagingOptions.TotalCount);
        m_WeakReference.get().DBClose();
        return m_lstT;
    }

    public <T> List<T> getListByPara(Class<T> t, String pStrSearchInfo, JqGridParam jqgridparam, int p_intIndex, String selectColumn) {
        List<Filter> mLstFilter = new ArrayList<Filter>();
        if (!StringExtend.Empty(pStrSearchInfo)) {
            mLstFilter = CommonHelper.JsonToModel(pStrSearchInfo,Filter.class);
        }
        return getListByPara(t,mLstFilter,jqgridparam,p_intIndex,selectColumn);
    }

    public <T> List<T> getListByPara(Class<T> t, String p_strSql, List<Filter> p_lstFilter, JqGridParam jqgridparam, int p_intIndex, String selectColumn) {
        PagingOptions m_PagingOptions = PagingOptions.Default();
        m_PagingOptions.PageSize = jqgridparam.getRows();
        m_PagingOptions.StartNumber = (jqgridparam.getPage() - 1) * m_PagingOptions.PageSize;
        m_PagingOptions.FetchTotalRecordCount = true;

        if (!StringExtend.Empty(selectColumn)) {
            m_PagingOptions.SelectColumn = selectColumn;
        }
        if (!StringExtend.Empty(jqgridparam.getSidx()) && !StringExtend.Empty(jqgridparam.getSord())) {
            m_PagingOptions.SortBy.add(new SortBy(jqgridparam.getSidx(), jqgridparam.getSord().toLowerCase() != "desc"));
        }

        WeakReference<DBExecutor> mWeakReference = new WeakReference<DBExecutor>(new DBExecutor(p_intIndex));
        List<T> m_lstT = mWeakReference.get().GetClassListByPara(t, p_strSql, m_PagingOptions, p_lstFilter);
        jqgridparam.setRecords(m_PagingOptions.TotalCount);
        mWeakReference.get().DBClose();
        return m_lstT;
    }

    public <T> int deleteInfo(Class<T> t, String keyValue, String parentId , int index ) {
        WeakReference<DBExecutor> mWeakReference = new WeakReference<DBExecutor>(new DBExecutor(index));
        int intReturn = 0;
        List<Filter> lstFilter = new ArrayList<Filter>();
        try {
            if (!StringExtend.Empty(parentId)) {
                lstFilter.add(Filter.Equal("ParentID", parentId));
                intReturn = mWeakReference.get().FindCount(t, lstFilter);
                if (intReturn > 0) {
                    return ErrorInfo.ExistingChildNodes.getCode();
                } else {
                    return mWeakReference.get().Delete(t, keyValue.split(","));
                }
            } else {
                intReturn = mWeakReference.get().Delete(t, keyValue.split(","));
            }
        } catch (Exception ex) {
            return ErrorInfo.SystemErrorFailure.getCode();
        }
        mWeakReference.get().DBClose();
        return intReturn;
    }

    /// <summary>
    /// 添加搜索条件
    /// </summary>
    /// <returns></returns>
    public static String GetSql(String searchKey,String searchField) {
        StringBuilder sb = new StringBuilder();
        if (StringExtend.Empty(searchKey) || StringExtend.Empty(searchField)) {
            return sb.toString();
        }
        String[] fieldArray = searchField.split(",");
        if (fieldArray.length <= 0) {
            return sb.toString();
        }
        sb.append(String.format(" AND (" + fieldArray[0] + " like '%%s%' ", searchKey));
        for (int i = 1; i < fieldArray.length; i++) {
            sb.append(String.format(" or " + fieldArray[i] + " like '%%s%' ", searchKey));
        }
        sb.append(" ) ");
        return sb.toString();
    }

    /// <summary>
    /// Sequence序列
    /// </summary>
    /// <param name="mStrColumnName">操作对象</param>
    /// <param name="db"></param>
    /// <param name="index"></param>
    public int GetSequence(String mStrColumnName , DBExecutor db , int index ) {
        DBExecutor mDbExecutor = (db==null) ?new DBExecutor(index):db;
        int mIntReturn = Integer.parseInt(mDbExecutor.GetValue("update SYS_Sequence set " + mStrColumnName + "=" + mStrColumnName + "+1 where SequenceID=1 ;select " + mStrColumnName + " from SYS_Sequence where SequenceID=1;"));
        if (db == null) {
            mDbExecutor.DBClose();
        }
        return mIntReturn;
    }

    /// <summary>
    /// 插入数据
    /// </summary>
    /// <param name="mObjEntity">操作对象</param>
    /// <param name="mStrParaInfo">主键</param>
    /// <param name="index"></param>
    public <T> int addOrSave(T mObjEntity, String mStrParaInfo, int index, ManageUser pManageUser) {
        Object mObjField = ReflectionUtil.GetKeyFieldValue(mObjEntity, "ParentID");//获取上一层ID
        T mModule = null;
        Hashtable mHtPara = new Hashtable();
        WeakReference<DBExecutor> m_WeakReference = new WeakReference<DBExecutor>(new DBExecutor(index));
        m_WeakReference.get().BeginTransaction();
        int isOk = 0;
        try {
            if (StringExtend.Empty(mStrParaInfo)) {
                isOk = m_WeakReference.get().Insert(mObjEntity, true);
                if (isOk < 1) {
                    m_WeakReference.get().RollbackTransection();
                    return isOk;
                }
            } else {
                isOk = Integer.parseInt(mStrParaInfo);
            }
            ReflectionUtil.SetKeyFieldValue(mObjEntity, isOk);

            List<Filter> mLstFilter = new ArrayList<Filter>();
            mLstFilter.add(ReflectionUtil.GetKeyFilter(mObjEntity));
            if (mObjField != null && Integer.parseInt(mObjField.toString()) != 0) {
                mModule = m_WeakReference.get().GetClassByPara((Class<T>) mObjEntity.getClass().newInstance(), mLstFilter);
            }
            if (mModule != null) {
                Object mObjFirstId = ReflectionUtil.GetKeyFieldValue(mModule, "FirstID");
                if (mObjFirstId != null) {
                    mHtPara.put("FirstID", mObjFirstId);
                }
                Object mObjCompanyId = ReflectionUtil.GetKeyFieldValue(mModule, "CompanyID");
                if (mObjCompanyId != null) {
                    mHtPara.put("CompanyID", mObjCompanyId);
                }
                Object mObjFlag = ReflectionUtil.GetKeyFieldValue(mModule, "Flag");
                if (mObjFlag != null) {
                    mHtPara.put("Flag", mObjFlag + "," + isOk);
                }
                Object mObjFlagName = ReflectionUtil.GetKeyFieldValue(mModule, "FlagName");
                if (mObjFlagName != null) {
                    mHtPara.put("FlagName", mObjFlagName + "," + ReflectionUtil.GetKeyFieldValue(mObjEntity, "CName"));
                }
                Object mObjALevel = ReflectionUtil.GetKeyFieldValue(mModule, "ALevel");
                if (mObjALevel != null) {
                    mHtPara.put("ALevel", Integer.parseInt(mObjALevel.toString()) + 1);
                }
            } else {
                if (ReflectionUtil.IsKeyField(mObjEntity, "ALevel")) {
                    mHtPara.put("ALevel", 1);
                }
                if (ReflectionUtil.IsKeyField(mObjEntity, "FirstID")) {
                    mHtPara.put("FirstID", isOk);
                }
                if (ReflectionUtil.IsKeyField(mObjEntity, "Flag")) {
                    mHtPara.put("Flag", String.valueOf(isOk));
                }
                if (ReflectionUtil.IsKeyField(mObjEntity, "FlagName")) {
                    mHtPara.put("FlagName", ReflectionUtil.GetKeyFieldValue(mObjEntity, "CName"));
                }
                if (ReflectionUtil.IsKeyField(mObjEntity, "CompanyID") && ReflectionUtil.GetKeyFieldValue(mObjEntity, "CompanyID") == null) {
                    mHtPara.put("CompanyID", pManageUser.CID);
                }
            }

            if (!StringExtend.Empty(mStrParaInfo)) {
                if (ReflectionUtil.IsKeyField(mObjEntity, "UpdatedDate")) {
                    mHtPara.put("UpdatedDate", new Date());
                }
                if (ReflectionUtil.IsKeyField(mObjEntity, "UpdatedUserID")) {
                    mHtPara.put("UpdatedUserID", pManageUser.UID);
                }
                if (ReflectionUtil.IsKeyField(mObjEntity, "UpdatedUserName")) {
                    mHtPara.put("UpdatedUserName", pManageUser.UN);
                }
                ReflectionUtil.SetKeyFieldValue(mObjEntity, mHtPara);
                isOk = m_WeakReference.get().Update(mObjEntity);
            } else {
                if (ReflectionUtil.IsKeyField(mObjEntity, "CreatedDate") && StringExtend.Empty(ReflectionUtil.GetKeyFieldValue(mObjEntity, "CreatedDate").toString())) {
                    mHtPara.put("CreatedDate", new Date());
                }
                if (ReflectionUtil.IsKeyField(mObjEntity, "CreatedUserID")) {
                    mHtPara.put("CreatedUserID", pManageUser.UID);
                }
                if (ReflectionUtil.IsKeyField(mObjEntity, "CreatedUserName")) {
                    mHtPara.put("CreatedUserName", pManageUser.UN);
                }
                if (ReflectionUtil.IsKeyField(mObjEntity, "Status") && ReflectionUtil.GetKeyFieldValue(mObjEntity, "Status") == null) {
                    mHtPara.put("Status", 1);
                }
                ReflectionUtil.SetKeyFieldValue(mObjEntity, mHtPara);
                isOk = m_WeakReference.get().Update(mObjEntity);
            }
            if (isOk > 0) {
                m_WeakReference.get().CommitTransection();
            } else {
                m_WeakReference.get().RollbackTransection();
            }
            m_WeakReference.get().DBClose();
        } catch (Exception ex) {
            m_WeakReference.get().RollbackTransection();
            m_WeakReference.get().DBClose();
            ex.printStackTrace();
            isOk= ErrorInfo.SystemErrorFailure.getCode();
        }
        return isOk;
    }
}
