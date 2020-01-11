package com.ja.assets.new_code;

/**
 * Created by long on 17/4/6.
 */

public class Constants {


    public static class Url {
        //        public static String Host = "http://47.93.249.1:9100/";
        public static String Host = "http://10.0.26.42:8080/";


        public class Patrol {
            //巡检列表
            public static final String PatrolCheckList = "xunjian/list";
            //填写巡检信息
            public static final String insertRecord = "xunjian/insertRecord";

            //巡检记录
            public static final String JiluPatrolCheckList = "xunjian/inspect/list";
            //巡检记录详情
            public static final String inspectDetail = "xunjian/inspect/detail";
        }

        public class Inventory {
            //未盘点
            public static final String getZcCheckList = "zcCheck/getZcCheckList";
            //创建盘点
            public static final String zcCheckSave = "zcCheck/save";

            //资产列表
            public static final String zichanliebiao = "zcCheck/info";
            //扫码
            public static final String updateZcItemStatus = "zcCheck/updateZcItemStatus";
            //            完成后,修改资产状态
            public static final String finishAssetsStatus = "zcCheck/finishAssetsStatus";
            //            盘点记录列表
            public static final String checkRecordList = "zcCheck/checkRecordList";

        }


        public static final String UploadImage = "files";
    }


}
