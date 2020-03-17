package com.ja.assets.new_code.bussiness.bean.post;

import com.ja.assets.new_code.bussiness.bean.result.BfCheckItemListResultBean;
import com.ja.assets.new_code.bussiness.bean.result.BuyCheckItemListResultBean;

import java.util.ArrayList;

public class ShbCheckPostBean {
    public long bizid;
    public long todoid;
    public long itemStatus;
    public Integer againSubmit;
    public String neirong;
    public ArrayList<BfCheckItemListResultBean> flowTodoItems=new ArrayList<>();

}
