package com.ja.assets.new_code.bussiness.bean.result;

public class BaoxiuDetailBean {
    public long id;

    public String code;

    public String epcid;

    public String zcCodenum;

    public String zcName;

    public String glDeptName;

    public String repairDes;

    public String startUseTime;

    public String createTime;//保修开始时间


    public String auditTime;

    public String originalValue;

    public String  netvalue;

    public String imgUrl;

    public int status;

    /** 报修结果 */
    public Integer qrStatus;

    /** 维修信息. */
    // 剩余期限
    public String remainingperiod;

    // 到期期限
    public String useMonths;

    // 报修期限
    public String warrantyperiod;

    // 本地 委外
    public String repairMode;

    // 上门 送修
    public String deliverMode;

    // 服务商名称
    public String outCompany;

    // 服务商地址
    public String outAddress;

    // 维修联系人
    public String outUsername;

    // 维修电话
    public String outPhone;

    // 送修地址
    public String repairAddress;
}
