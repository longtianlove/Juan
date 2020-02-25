package com.ja.assets.new_code.bussiness.bean.post;

import com.ja.assets.new_code.bussiness.bean.result.RepairCheckItemListResultBean;

import java.util.ArrayList;

public class RepairCheckPostBean {
    public long zcRepairId;
    public long flowTodoId;
    public String neirong;
    ArrayList<RepairCheckItemListResultBean> zcRepairItemList=new ArrayList<>();
}
