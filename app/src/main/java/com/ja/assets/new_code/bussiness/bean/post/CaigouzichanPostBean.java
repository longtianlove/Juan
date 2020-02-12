package com.ja.assets.new_code.bussiness.bean.post;

import java.util.ArrayList;
import java.util.List;

public class CaigouzichanPostBean {
    /**
     * 部门名称.
     */
    public String companyName;
    /**
     * 附件相对地址.
     */
    public String fileUrl;
    /**
     * 附件名称.
     */
    public String fileName;
    /** 购买子项列表. */
    public List<Caigouitemzichan> zcBuyItemList=new ArrayList<>();
}
