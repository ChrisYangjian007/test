package com.dalian.sea.WebClass;

/**
 * Created by Administrator on 2017-10-11.
 */
public class JqGridResult implements java.io.Serializable {
    //总页数
    private Integer total = 0;
    //当前页
    private Integer page = 0;
    //总记录数
    private Integer records = 0;
    //耗时
    private String costtime = "";
    //数据
    private Object rows = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public String getCosttime() {
        return costtime;
    }

    public void setCosttime(String costtime) {
        this.costtime = costtime;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }
}
