package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-07-25.
 */
public class PagingOptions {
    public boolean FetchTotalRecordCount = false;
    public int StartNumber;
    public String SelectColumn;
    public int PageSize;
    public int TotalCount;
    public List<SortBy> SortBy = new ArrayList<SortBy>();

    public static PagingOptions Default() {
        PagingOptions m_PagingOptions = new PagingOptions();
        m_PagingOptions.StartNumber = 0;
        m_PagingOptions.PageSize = 0;
        return m_PagingOptions;
    }

    public static PagingOptions Info() {
        PagingOptions m_PagingOptions = new PagingOptions();
        m_PagingOptions.StartNumber = 0;
        m_PagingOptions.PageSize = 1;
        return m_PagingOptions;
    }

    public static PagingOptions FetchRecord() {
        PagingOptions m_PagingOptions = new PagingOptions();
        m_PagingOptions.StartNumber = 0;
        m_PagingOptions.PageSize = 30;
        return m_PagingOptions;
    }
}
