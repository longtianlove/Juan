package com.ja.assets.new_code.bussiness.bean.result;

import java.math.BigDecimal;

public class GetZcInfoResultBean {
    public int id;

    public String epcid;

    public String zcCodenum;

    public String zcName;


    public String zcCategoryName;

    public String syDeptName;

    /**
     * 资产来源
     */
    public String zcFrom;


    public String glDeptName;


    public String startUseTime;


    public int remainingperiod;

    /**
     * 净额
     */
    public BigDecimal net;

    /**
     * 原值
     */
    public BigDecimal originalValue;
}
