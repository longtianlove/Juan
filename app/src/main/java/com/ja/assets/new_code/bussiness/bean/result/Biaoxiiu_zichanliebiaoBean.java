package com.ja.assets.new_code.bussiness.bean.result;

import java.math.BigDecimal;

public class Biaoxiiu_zichanliebiaoBean {
    public int id;

    public int zcCheckId;

    public int zcId;

    public String zcName;

    public String storeAddress;


    public String syDeptName;

    public String glDeptName;

    public String zcCodenum;

    public String epcid;

    public boolean isChecked = false;


    public BigDecimal originalValue;

    public BigDecimal netvalue;

    /**
     * 调入部门id
     */
    public int backDeptId;

    /**
     * 调入部门名称
     */
    public String backUsername;

    public int syDeptId;
    public int glDeptId;

    public String imageUrl;

    //报修原因
    public String repair_des;
}
