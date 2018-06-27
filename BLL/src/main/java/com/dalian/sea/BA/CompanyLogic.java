package com.dalian.sea.BA;

import com.dalian.sea.Base.BaseLogic;
import Core.Data.DBExecutor;
import Extend.StringExtend;
import com.dalian.sea.Models.BA.Company;
import com.dalian.sea.Models.BA.FormAttributeValue;
import Models.Enum.CurrentDatabase;
import Models.Enum.ErrorInfo;
import Models.Filter;
import Models.PagingOptions;
import com.dalian.sea.WebClass.ManageUser;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-10-20.
 */
public class CompanyLogic {
    private static CompanyLogic _item;

    public static CompanyLogic Instance() {
        if (_item == null) {
            _item = new CompanyLogic();
        }
        return _item;
    }

    /// <summary>
    /// 获取公司列表
    /// </summary>
    public List<Company> GetList(int ParentId, ManageUser manageUser, String searchKey, String searchField) {
        StringBuilder strSql = new StringBuilder();
        strSql.append("select CompanyID,ParentID,Category,Code,CName,ShortName,IndustryType,CorporateRep,Contact,Phone,Fax,Email,Address,AccountInfo,Postalcode,WebUrl,Remark,Longitude,Latitude,AreaID,AreaName,CompanyNature,RegisterFund,BusinessScope,TaxNo,TaxRegDate,OrgCode,BizRegCode,CorporateRepIDNo,BizLicenseNo,Status from BA_Company M Where 1=1 ");
        if (ParentId != -1) {
            strSql.append(" and ParentID= " + ParentId);
        }
        strSql.append(BaseLogic.GetSql(searchKey, searchField));
        if (manageUser.ISys != 1) {
            strSql.append(" AND ( CompanyID IN ( SELECT ResourceID FROM BA_DataScopePermission WHERE");
            strSql.append(" ObjectID IN ('" + manageUser.OID.replace(",", "','") + "') ");
            strSql.append(" ) )");
        }
        strSql.append(" ORDER BY ListIndex ASC");
        WeakReference<DBExecutor> mWeakReference = new WeakReference<DBExecutor>(new DBExecutor(CurrentDatabase.MainDB.getCode()));
        List<Filter> lstFilter = new ArrayList<Filter>();
        List<Company> lstCompany = mWeakReference.get().GetClassListByPara(Company.class, strSql.toString(), PagingOptions.Default(), lstFilter);
        mWeakReference.get().DBClose();
        return lstCompany;
    }

    /// <summary>
    /// 【公司管理】提交表单
    /// </summary>
    /// <param name="entity">实体对象</param>
    /// <param name="KeyValue">主键值</param>
    /// <param name="BuildFormJson">自定义表单</param>
    public int SubmitCompany(final Company entity, String KeyValue, final String BuildFormJson, ManageUser manageuser) {
        int IsOk = 0;
        List<Filter> lstFilter = new ArrayList<Filter>();
        final WeakReference<DBExecutor> mWeakReference = new WeakReference<DBExecutor>(new DBExecutor(CurrentDatabase.MainDB.getCode()));
        try {
            mWeakReference.get().BeginTransaction();
            if (!StringExtend.Empty(KeyValue)) {
                entity.setCompanyID(Integer.parseInt(KeyValue));
                entity.setUpdatedDate(new Date());
                entity.setUpdatedUserID(manageuser.UID);
                entity.setUpdatedUserName(manageuser.UN);
                IsOk = mWeakReference.get().Update(entity);
            } else {
                entity.setCompanyID(BaseLogic.Instance().GetSequence("MainID", mWeakReference.get(), CurrentDatabase.MainDB.getCode()));//获取主键
                entity.setCreatedDate(new Date());
                entity.setCreatedUserID(manageuser.UID);
                entity.setCreatedUserID(manageuser.UID);
                IsOk = mWeakReference.get().Insert(entity,false);
            }
            FormAttributeValue formattributevalue = new FormAttributeValue() {{
                setAttributeValueID(BaseLogic.Instance().GetSequence("MainID", mWeakReference.get(), CurrentDatabase.MainDB.getCode()));
                setObjectID(entity.getCompanyID());
//                setModuleID();
//                ModuleID = SecurityHelper.Decrypt(CookieCache.GetCookie("MID")).ConvertType <int>(),
                setObjectParameterJson(BuildFormJson);
            }};
            lstFilter.add(Filter.Equal("ObjectID", entity.getCompanyID()));
            mWeakReference.get().Delete("DELETE FROM BA_FormAttributeValue WHERE ObjectID = ?", lstFilter);
            IsOk = mWeakReference.get().Insert(formattributevalue,true);
        } catch (Exception ex) {
            IsOk = ErrorInfo.SystemErrorFailure.getCode();
        }
        if (IsOk > 0) {
            mWeakReference.get().CommitTransection();
        } else {
            mWeakReference.get().RollbackTransection();
        }
        mWeakReference.get().DBClose();
        return IsOk;
    }
}
