package Models;

/**
 * Created by Administrator on 2017-07-30.
 */
public class JqGridParam {

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getTotal() {
        if (records > 0 && this.rows > 0) {
            return records % this.rows == 0 ? records / this.rows : records / this.rows + 1;
        }
        return 1;
    }

    /// 每页行数
    private int rows ;
    /// 当前页
    private int page ;
    /// 排序列
    private String sidx ;
    /// 排序类型
    private String sord ;
    /// 总记录数
    private int records ;
    /// 总页数
    private int total;
}
