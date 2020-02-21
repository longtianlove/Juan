package com.ja.assets.new_code.bussiness.bean.post;

import com.ja.assets.new_code.bussiness.bean.result.BuyCheckItemListResultBean;

import java.util.ArrayList;

public class BuyCheckPostBean {
    public long zcBuyId;
    public long itemStatus;
    public String neirong;
    public String againSubmit;
    public ArrayList<BuyCheckItemListResultBean> flowTodoItems=new ArrayList<>();
}
