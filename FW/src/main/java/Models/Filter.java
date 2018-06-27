package Models;

import Models.Enum.ComparisonDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-07-22.
 */
public class Filter implements Serializable {

    private String Name;
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    private Object Value;
    public Object getValue() {
        return Value;
    }
    public void setValue(Object value) {
        Value = value;
    }
    private Filter LFilter;
    public Filter getLFilter() {
        return LFilter;
    }
    public void setLFilter(Filter LFilter) {
        this.LFilter = LFilter;
    }
    private Filter RFilter;
    public Filter getRFilter() {
        return RFilter;
    }
    public void setRFilter(Filter RFilter) {
        this.RFilter = RFilter;
    }
    private ComparisonDTO Comparison;
    public ComparisonDTO getComparison() {
        return Comparison;
    }
    public void setComparison(ComparisonDTO comparison) {
        Comparison = comparison;
    }
    private List<Object> ParaList=new ArrayList<Object>();
    public List<Object> getParaList() {
        return ParaList;
    }
    public void setParaList(List<Object> paraList) {
        ParaList = paraList;
    }

    public Filter() {
    }
    public Filter(String name, ComparisonDTO comparisonDTO, Object value) {
        Name = name;
        Comparison = comparisonDTO;
        Value = value;
    }
    public static Filter Equal(String name, Object value) {
        return new Filter(name, ComparisonDTO.Equal, value);
    }
    public static Filter NotEqual(String name, Object value) {
        return new Filter(name, ComparisonDTO.NotEqual, value);
    }
    public static Filter GreaterThan(String name, Object value) {
        return new Filter(name, ComparisonDTO.GreaterThan, value);
    }
    public static Filter LessThan(String name, Object value) {
        return new Filter(name, ComparisonDTO.LessThan, value);
    }
    public static Filter GreaterEqualThan(String name, Object value) {
        return new Filter(name, ComparisonDTO.GreaterEqualThan, value);
    }
    public static Filter LessEqualThan(String name, Object value) {
        return new Filter(name, ComparisonDTO.LessEqualThan, value);
    }
    public static Filter In(String name, Collection value) {
        return new Filter(name, ComparisonDTO.In, value);
    }
    public static Filter Like(String name, String value) {
        return new Filter(name, ComparisonDTO.Like, value);
    }
    public static Filter EqualProperty(String name, String property) {
        return new Filter(name, ComparisonDTO.EqualProperty, property);
    }
    public static Filter NotEqualProperty(String name, String property) {
        return new Filter(name, ComparisonDTO.NotEqualProperty, property);
    }
    public static Filter GreaterThanProperty(String name, String property) {
        return new Filter(name, ComparisonDTO.GreaterThanProperty, property);
    }
    public static Filter LessThanProperty(String name, String property) {
        return new Filter(name, ComparisonDTO.LessThanProperty, property);
    }
    public static Filter GreaterEqualThanProperty(String name, String property) {
        return new Filter(name, ComparisonDTO.GreaterEqualThanProperty, property);
    }
    public static Filter LessEqualThanProperty(String name, String property) {
        return new Filter(name, ComparisonDTO.LessEqualThanProperty, property);
    }
    public static Filter And(Filter lFilter, Filter rFilter) {
        Filter m_Filter = new Filter();
        m_Filter.setComparison(ComparisonDTO.And);
        m_Filter.setRFilter(rFilter);
        m_Filter.setLFilter(lFilter);
        m_Filter.setName(new String());
        m_Filter.setValue(null);
        return m_Filter;
    }
    public static Filter Or(Filter lFilter, Filter rFilter) {
        Filter m_Filter = new Filter();
        m_Filter.setComparison(ComparisonDTO.OR);
        m_Filter.setRFilter(rFilter);
        m_Filter.setLFilter(lFilter);
        m_Filter.setName(new String());
        m_Filter.setValue(null);
        return m_Filter;
    }
    public static Filter Not(Filter lFilter) {
        Filter m_Filter = new Filter();
        m_Filter.setComparison(ComparisonDTO.Not);
        m_Filter.setRFilter(null);
        m_Filter.setLFilter(lFilter);
        m_Filter.setName(new String());
        m_Filter.setValue(null);
        return m_Filter;
    }
    public static Filter IsNull(String name) {
        return new Filter(name, ComparisonDTO.IsNull, null);
    }
    public static Filter Sql(String sql) {
        return new Filter("", ComparisonDTO.Sql, sql);
    }

    private Object target;
    public String ToSqlString() {
        return ToSqlString(0, null);
    }
    public String ToSqlString(Map<String, String> p_Dic) {
        return ToSqlString(0, p_Dic);
    }
    public String ToSqlString(int p_intItem, Map<String, String> p_Dic) {
        String m_strTmp = new String();
        if (Comparison == ComparisonDTO.Like) {
            String format;
            format = " %s like ? ";
            ParaList.add(Value);
            return String.format(format, (p_Dic == null) ? Name : p_Dic.get(Name), Name);
        }
        if (Comparison == ComparisonDTO.And) {
            m_strTmp = String.format("(%s AND %s)", LFilter.ToSqlString(p_intItem, p_Dic), RFilter.ToSqlString(p_intItem, p_Dic));
            ParaList.addAll(LFilter.getParaList());
            ParaList.addAll(RFilter.getParaList());
            return m_strTmp;
        }
        if (Comparison == ComparisonDTO.OR) {
            m_strTmp = String.format("(%s OR %s)", LFilter.ToSqlString(p_intItem, p_Dic), RFilter.ToSqlString(p_intItem, p_Dic));
            ParaList.addAll(LFilter.getParaList());
            ParaList.addAll(RFilter.getParaList());
            return m_strTmp;
        }
        if (Comparison == ComparisonDTO.Not) {
            m_strTmp = String.format("(NOT %s)", LFilter.ToSqlString(p_intItem, p_Dic));
            ParaList.addAll(LFilter.ParaList);
            return m_strTmp;
        }
        if (Comparison == ComparisonDTO.Sql) {
            return String.format("(%s)", Value);
        }
        return ToHqlString(p_intItem, p_Dic);
    }
    public String ToHqlString(int p_intItem, Map<String, String> p_Dic) {
        Object conditionValue = getValue();
        String hqlParameter = "?";
        String m_strTmp = new String();
        String hqlCondition = new String();
        if (Comparison != ComparisonDTO.In && Comparison != ComparisonDTO.IsNull) {
            ParaList.add(conditionValue);
        }
        switch (Comparison) {
            case Equal:
                hqlCondition = String.format(" %s = %s ", (p_Dic == null) ? Name : p_Dic.get(Name), hqlParameter);
                break;
            case NotEqual:
                hqlCondition = String.format(" %s != %s ", (p_Dic == null) ? Name : p_Dic.get(Name), hqlParameter);
                break;
            case GreaterThan:
                hqlCondition = String.format(" %s > %s ", (p_Dic == null) ? Name : p_Dic.get(Name), hqlParameter);
                break;
            case GreaterEqualThan:
                hqlCondition = String.format(" %s >= %s ", (p_Dic == null) ? Name : p_Dic.get(Name), hqlParameter);
                break;
            case LessThan:
                hqlCondition = String.format(" %s < %s ", (p_Dic == null) ? Name : p_Dic.get(Name), hqlParameter);
                break;
            case LessEqualThan:
                hqlCondition = String.format(" %s <= %s ", (p_Dic == null) ? Name : p_Dic.get(Name), hqlParameter);
                break;
            case In:
                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    sb.append("(");
                    for(Object item : (Collection)conditionValue){
                        ParaList.add(item);
                        sb.append(" ? ,");
                    }
                    sb.append(") ");
                    hqlCondition = String.format(" %s in %s ", (p_Dic == null) ? Name :p_Dic.get(Name), sb.toString().replace(",)", ")"));
                break;
            case Like:
                String format = " %s like %s ";
                hqlCondition = String.format(format, (p_Dic == null) ? Name : p_Dic.get(Name), hqlParameter);
                break;
            case EqualProperty:
                hqlCondition = String.format(" %s = %s ", (p_Dic == null) ? Name : p_Dic.get(Name), conditionValue);
                break;
            case NotEqualProperty:
                hqlCondition = String.format(" %s != %s ", (p_Dic == null) ? Name : p_Dic.get(Name), conditionValue);
                break;
            case GreaterThanProperty:
                hqlCondition = String.format(" %s > %s ", (p_Dic == null) ? Name : p_Dic.get(Name), conditionValue);
                break;
            case GreaterEqualThanProperty:
                hqlCondition = String.format(" %s >= %s ", (p_Dic == null) ? Name : p_Dic.get(Name), conditionValue);
                break;
            case LessThanProperty:
                hqlCondition = String.format(" %s < %s ", (p_Dic == null) ? Name : p_Dic.get(Name), conditionValue);
                break;
            case LessEqualThanProperty:
                hqlCondition = String.format(" %s <= %s ", (p_Dic == null) ? Name : p_Dic.get(Name), conditionValue);
                break;
            case And:
                hqlCondition = String.format("(%s AND %s)", LFilter.ToHqlString(p_intItem, p_Dic), RFilter.ToHqlString(p_intItem, p_Dic));
                ParaList.addAll(LFilter.ParaList);
                ParaList.addAll(RFilter.ParaList);
                break;
            case OR:
                hqlCondition = String.format("(%s OR %s)", LFilter.ToHqlString(p_intItem, p_Dic), RFilter.ToHqlString(p_intItem, p_Dic));
                ParaList.addAll(LFilter.ParaList);
                ParaList.addAll(RFilter.ParaList);
                break;
            case Not:
                hqlCondition = String.format("(NOT %s)", LFilter.ToHqlString(p_intItem, p_Dic));
                ParaList.addAll(LFilter.ParaList);
                break;
            case IsNull:
                hqlCondition = String.format("(%s is null)", (p_Dic == null) ? Name : p_Dic.get(Name));
                break;
            case Sql:
                hqlCondition = String.format("(%s)", conditionValue);
                break;
            default:
                hqlCondition ="";
                break;
        }
        return hqlCondition;
    }
    private static String ToHqlParameter(Object value) {
        if (!(value instanceof String)) return value.toString();
        return String.format("'%s'", value.toString().replace("'", "''"));
    }
    private String ToHqlParameter(Collection value) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Object item : value)
            sb.append(" " + ToHqlParameter(item) + " ,");
        sb.append(") ");
        return sb.toString().replace(",)", ")");
    }
}
