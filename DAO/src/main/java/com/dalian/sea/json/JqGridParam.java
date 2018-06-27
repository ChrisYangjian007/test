package com.dalian.sea.json;

import lombok.Data;

import java.io.Serializable;

/**
 * JqGridParam JqGrid表格参数
 *
 * @author xintao
 * @date 2018/1/17
 */
@Data
public class JqGridParam implements Serializable {

    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页行数
     */
    private Integer rows;
    /**
     * 总记录数
     */
    private Long records;
    /**
     * 总页数
     */
    private Integer total;

    /**
     * 返回数据 root
     */
    public Object root;

    public JqGridParam(Integer page, Integer rows, Long records, Integer total, Object root) {
        this.page = page;
        this.rows = rows;
        this.records = records;
        this.total = total;
        this.root = root;
    }

}
