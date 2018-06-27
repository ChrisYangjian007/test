package Core.Data;

import Core.ORM.Mapping;
import Core.ORM.ModelCache;
import Extend.StringExtend;
import Models.Annotations.ColumnAttribute;

import java.lang.reflect.Field;
import java.util.*;

/**
* Created by Administrator on 2017-08-10.
*/
public abstract class DBBuilder <T> {

    /// 实体类映射
    protected Mapping Map;
    private String _TableName = "";
    public String getTableName() {
        return _TableName;
    }
    public void setTableName(String _TableName) {
        this._TableName = _TableName;
    }

    /// 条件参数
    public List<Object> ParamsList = new ArrayList<Object>();
    /// 字段选择Sql
    protected StringBuilder SelectString = new StringBuilder();
    /// 排序Sql
    protected StringBuilder SortString = new StringBuilder();
    /// 条件Sql
    protected StringBuilder WhereString = new StringBuilder();

    private DBProvider dbProvider;
    public DBProvider getDbProvider() {
        return dbProvider;
    }
    public void setDbProvider(DBProvider dbProvider) {
        this.dbProvider = dbProvider;
    }

    /// 数据库Sql生成
    /// <param name="tableName">表名称</param>
    public DBBuilder(String tableName) {
        _TableName = tableName;
    }

    public String LastIdentity() {
        return "SELECT @@IDENTITY;";
    }

    public String GetFields() {
        if (SelectString.length() > 0) {
            return SelectString.toString();
        }

        StringBuilder str = new StringBuilder();
        Iterator iter = Map.ModelList.entrySet().iterator();
        while (iter.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) iter.next();
            ColumnAttribute val = (ColumnAttribute) entry.getValue();
            if (!val.Ignore()) {
                str.append(dbProvider.CreateTableAegis(val.Name()) + ",");
            }
        }
        if (StringExtend.Empty(str.toString())) {
            return "*";
        }
        return StringExtend.Trim(str.toString(), ",");
    }
//
//    public String Insert(TInfo info)
//    {
//        ParamsList = GetParameter(info, false);
//        if (ParamsList.Count == 0)
//        {
//            return string.Empty;
//        }
//
//        //要插入的表
//        var sql = new StringBuilder();
//
//        sql.AppendFormat("INSERT INTO {0} ", TableName);
//
//        #region 要插入的值
//
//        var strFields = new StringBuilder("(");
//        var strValues = new StringBuilder("(");
//
//        foreach (var param in ParamsList)
//        {
//            //if (param.ParameterName.Equals("@ID"))
//            //{
//            //    continue;
//            //}
//            strFields.AppendFormat("{0},", dbProvider.CreateTableAegis(param.ParameterName.Substring(1)));
//            strValues.AppendFormat("{0},", param.ParameterName);
//        }
//
//        sql.AppendFormat(strFields.ToString().DelEndOf(",") + ") VALUES " + strValues.ToString().DelEndOf(",") + ")");
//
//        #endregion
//
//        return sql.ToString() + ";";
//    }
//

    public String Count() {
        return String.format("SELECT Count(0) FROM %s %s;", _TableName, WhereString);
    }
    public String Delete(T info) throws IllegalAccessException {
        if (StringExtend.Empty(WhereString.toString())) {
            Map<String, Object> m_map = GetParameter(info);
            if (m_map.size() == 0) {
                return "";
            }
            WhereString.append(" WHERE " + Map.getIndexName() + " = ?");
            ParamsList.add(m_map.get(Map.getIndexName()));
        }
        return String.format("DELETE FROM %s %s;", _TableName, WhereString);
    }
    public String ToInfo() {
        return String.format("select top 1 {0} from %s %s %s ;", GetFields(), _TableName, WhereString, SortString);
    }

//
//    public String ToTable(int pageSize, int pageIndex)
//    {
//        // 不分页
//        if (pageIndex == 1)
//        {
//            return ToTable(pageSize);
//        }
//
//        if (SortString.Length == 0)
//        {
//            SortString.AppendFormat("ORDER BY {0} ASC", Map.IndexName);
//        }
//        var sort2 = SortString.ToString().Replace(" DESC", " [倒序]").Replace("ASC", "DESC").Replace("[倒序]", "ASC");
//
//        return string.Format("SELECT TOP {1} {0} FROM (SELECT TOP {2} {0} FROM {3} {4} {5}) a  {6};",
//                GetFields(), pageSize, pageSize * pageIndex, TableName, WhereString, SortString, sort2);
//    }
//
//    public String ToTable(int top = 0)
//    {
//        var topString = top > 0 ? string.Format("TOP {0}", top) : string.Empty;
//        return string.Format("SELECT {0} {1} FROM {2} {3} {4};", topString, GetFields(), TableName, WhereString,
//                SortString);
//    }
//
//    public String ToTableByRand(int top = 0)
//    {
//        var topString = top > 0 ? string.Format("TOP {0}", top) : string.Empty;
//        return string.Format("SELECT {0} {1} FROM {2} {3} ORDER BY NEWID();", topString, GetFields(), TableName,
//                WhereString);
//    }
//
//    public String Update(TInfo info)
//    {
//        var param = GetParameter(info,false);
//        if (param.Count == 0) return string.Empty;
//
//        // 要更新的表
//        var sql = new StringBuilder();
//        sql.AppendFormat("UPDATE {0} SET ", TableName);
//
//        ParamsList.AddRange(param);
//
//        if (WhereString.IsNullOrEmpty())
//        {
//            WhereString.Append(" WHERE " + Map.IndexName + " = " + dbProvider.ParamsPrefix + Map.IndexName);
//            if (!ParamsList.Exists(p => p.ParameterName.Substring(1) == Map.IndexName)) ParamsList.Add(dbProvider.CreateDbParam(Map.IndexName, info.ID));
//            // 要更新的字段
//            foreach (var parm in param)
//            {
//                if (parm.ParameterName.Substring(1) == Map.IndexName) continue;
//                sql.AppendFormat("{0} = {1} ,", dbProvider.CreateTableAegis(parm.ParameterName.Substring(1)), parm.ParameterName);
//            }
//        }
//        else
//        {
//            foreach (var parm in param)
//            {
//                sql.AppendFormat("{0} = {1} ,", dbProvider.CreateTableAegis(parm.ParameterName.Substring(1)), parm.ParameterName);
//            }
//        }
//        return sql.ToString().DelEndOf(",") + WhereString + ";";
//    }
//
//    public String UpdateValue<T>(T fieldValue)
//    {
//        var lst = SelectString.ToString().ToList("");
//        for (int i = 0; i < lst.Count; i++) { lst[i] = string.Format("{0} = {0} + {1},", lst[i], fieldValue); }
//        return string.Format("UPDATE {0} SET {1} {2};", TableName, lst.ToString("").DelEndOf(","), WhereString);
//    }
//
//    public String GetSum()
//    {
//        return string.Format("SELECT SUM({0}) FROM {1} {2};", SelectString, TableName, WhereString);
//    }
//
//    public String GetMax()
//    {
//        return string.Format("SELECT MAX({0}) FROM {1} {2};", SelectString, TableName, WhereString);
//    }
//
//    public String GetMin()
//    {
//        return string.Format("SELECT MIN({0}) FROM {1} {2};", SelectString, TableName, WhereString);
//    }
//
//    public String GetValue()
//    {
//        return string.Format("SELECT TOP 1 {0} FROM {1} {2};", SelectString, TableName, WhereString);
//    }
//
//    public String ResetIdentity()
//    {
//        throw new Exception("该数据库不支持此方法");
//    }
//
//    public void Clear() {
//        ParamsList.Clear();
//        SelectString.setLength(0);
//        SortString.setLength(0);
//        WhereString.setLength(0);
//    }

    /// 获取该实体类的参数
/// <param name="info">实体类</param>
    protected Map<String, Object> GetParameter(T info) throws IllegalAccessException {
        Map<String, Object> m_map = new HashMap<String, Object>();
        Map = ModelCache.GetInfo(info.getClass());
        Iterator iter = Map.ModelList.entrySet().iterator();
        while (iter.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) iter.next();
            Field key = (Field) entry.getKey();
            ColumnAttribute val = (ColumnAttribute) entry.getValue();
            key.setAccessible(true);
            if (!val.Ignore()) {
                m_map.put(val.Name(), key.get(info));
            }
        }
        return m_map;
    }

    public String Insert(T info) throws IllegalAccessException {
        Map<String, Object> m_map = GetParameter(info);
        if (m_map.size() == 0) {
            return "";
        }
        //要插入的表
        StringBuilder sql = new StringBuilder();

        sql.append(String.format("INSERT INTO %s ", _TableName));
        ParamsList.clear();
        StringBuilder strFields = new StringBuilder("(");
        StringBuilder strValues = new StringBuilder("(");
        Iterator iter = m_map.entrySet().iterator();
        while (iter.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) iter.next();
            String key = (String) entry.getKey();
            if (entry.getValue() == null) {
                continue;
            }//值为空
            strFields.append(String.format("%s,", dbProvider.CreateTableAegis(key)));
            strValues.append(String.format("%s,", "?"));
            ParamsList.add((Object) entry.getValue());
        }
        sql.append(StringExtend.Trim(strFields.toString(), ",") + ") VALUES " + StringExtend.Trim(strValues.toString(), ",") + ")");

        return sql.toString() + ";";
    }

    public String Update(T info) throws IllegalAccessException {
        Map<String, Object> m_map = GetParameter(info);
        if (m_map.size() == 0) {
            return "";
        }

        // 要更新的表
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("UPDATE %s SET ", _TableName));
        ParamsList.clear();

        Iterator iter = m_map.entrySet().iterator();
        while (iter.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) iter.next();
            String key = (String) entry.getKey();
            if (key == Map.getIndexName()) continue;
            if (entry.getValue() == null) {
                continue;
            }//值为空
            sql.append(String.format("%s = ? ,", dbProvider.CreateTableAegis(key)));
            ParamsList.add((Object) entry.getValue());
        }
        if (StringExtend.Empty(WhereString.toString())) {
            WhereString.append(" WHERE " + Map.getIndexName() + " = ?");
            ParamsList.add(m_map.get(Map.getIndexName()));
        }
        return StringExtend.Trim(sql.toString(), ",") + WhereString + ";";
    }
}
