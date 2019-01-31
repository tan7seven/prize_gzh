package com.prize.prize_gzh.utils;

/**
 * @Description:类说明：分页实体
 * @author: gzh
 * @date: 2018年9月23日下午3:58:34
 */
public class PageBean {
	
    /**
     * 当前第几页
     */
    private Integer start;
    
    /**
     * 每页显示多少条
     */
    private Integer rows;

    public Integer getStart() {
        return start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
