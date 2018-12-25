package com.yuxiang.li.reptile.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yuxiang.li on 2018/12/3.
 * 每一次开奖的实体
 */
public class TrEntity implements Serializable {

    /**
     * 期次
     */
    private Integer period;

    /**
     * 时间
     */
    private Date date;

    /**
     * 开奖的最后一位
     */
    private Integer result;

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
