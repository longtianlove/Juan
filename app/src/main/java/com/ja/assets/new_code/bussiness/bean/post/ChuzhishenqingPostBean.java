package com.ja.assets.new_code.bussiness.bean.post;

import com.ja.assets.new_code.bussiness.bean.result.Chuzhi_zichanliebiaoBean;

import java.util.List;

public class ChuzhishenqingPostBean {
    //    {
//        "bfCategory": 0,
//            "bfDes": "string",
//            "type": 0,
//            "zcBfItemList": [
//        {
//            "bz": "string",
//                "epcid": "string",
//                "fileName": "string",
//                "glDeptId": 0,
//                "glDeptName": "string",
//                "id": 0,
//                "netvalue": 0,
//                "originalValue": 0,
//                "remainingperiod": 0,
//                "storeAddress": "string",
//                "syDeptId": 0,
//                "syDeptName": "string",
//                "zcCodenum": "string",
//                "zcName": "string"
//        }
//  ]
//    }
    public int bfCategory = 1;
    public String bfDes = "test";
    public int type = 1;
    public List<Chuzhi_zichanliebiaoBean> zcBfItemList;
}
