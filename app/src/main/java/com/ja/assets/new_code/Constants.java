package com.ja.assets.new_code;

/**
 * Created by long on 17/4/6.
 */

public class Constants {


    public static class Url {
        //        public static String Host = "http://47.93.249.1:9100/";
        public static String Host = "http://testyq.17sys.cn/";
//        public static String Host = "http://10.0.26.42:8080/";
//        public static String Host = "http://172.20.10.3:8080/";


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

            //            获取盘点报表--基本信息
            public static final String checkReport_base = "zcCheck/checkReport";

            //            获取盘点报表信息--盘盈/盘亏列表
            public static final String profitList = "zcCheck/checkReport/profitList";

        }

        public class DiaoPei {
            //            获取调配资产的列表数据
            public static final String diaopei_zichanliebiao = "deploy/zc/deployList";
            //            获取eleTree部门树列表
            public static final String diaopei_bumenlist = "deploy/dept/subDeptList";

            //            添加资产调配的数据信息
            public static final String insertZcDeployData = "deploy/zc/insertZcDeployData";


            //            获取调配记录的列表
            public static final String deployRecordList = "deploy/deployRecordList";


            //            获取调配记录找到子记录数据
            public static final String listByZcDeployId = "deploy/listByZcDeployId";

        }

        public class BaoXiu {
            //            获取保修的资产列表
            public static final String repairList = "app/repair/list";

            //            提交上传的报修的信息
            public static final String insertRepairData = "app/repair/insertRepairData";
            //            获取保修的【记录列表】
            public static final String repairRecordList = "app/repair/repair/recordList";
            //            报修详情
            public static final String listByZcReId = "app/repair/listByZcReId";

        }

        public class ChuZhi {
            //            获取资产报废的列表
            public static final String dealBfList = "deal/bfList";
            //            添加报废记录申请
            public static final String insertBfData = "deal/insertBfData";
            //            获取报废的记录列表
            public static final String getBFRecordList = "deal/getBFRecordList";
            //            获取报废的记录列表下面的详情数据列表
            public static final String getBFRecordItemList = "deal/getBFRecordItemList";
        }

        public class Me {
            //            修改密码
            public static final String updatePassword = "mine/updatePassword";
        }

        public static final String UploadImage = "files";
    }


}
