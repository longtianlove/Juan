package com.ja.assets.new_code.bussiness.bean.result;

import java.util.Date;

public class ZiChansBean {
    private int id;

    /**
     * 资产追溯码
     */
    public String epcid;

    /**
     * 使用部门id
     */
    public long syDeptId;

    /**
     * 使用部门名称
     */
    public String syDeptName;


    /**
     * 管理部门id
     */
    public long glDeptId;


    /**
     * 管理部门名称
     */
    public String glDeptName;


    /**
     * 存储地址
     */
    public String storeAddress;


    /**
     * 资产名称
     */
    public String zcName;


    /**
     * 创建时间
     */
    public String createTime;


    /**
     * 巡检状态
     */
    public int status;

    /**
     * 巡检周期
     */
    public String inspectTime;


    /**
     * 巡检结果
     */
    public int result;


    /**
     * 资产编码
     */
    public String zcCodenum;


    /**
     * 巡检关系id
     */
    public int zcRealId;
}
