package com.ja.assets.new_code;

/**
 * Created by long on 17/4/6.
 */

public class Constants {


    public static class Url {
        //        public static String Host = "http://47.93.249.1:9100/";
//        public final static String Host = "http://u38n5x.natappfree.cc/";

        public final static String Host = "http://testyq.17sys.cn/";
        public final static String FILE_HOST = "http://testyq.17sys.cn/statics";

//        public final static String Host = "http://192.168.1.17:8080/";
//        public final static String FILE_HOST = "http://192.168.1.17:8080/statics";


//        public final static String Host = "http://xfmkgh.natappfree.cc/";


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
            public static final String diaopei_bumenlist = "appDept/deptList";

            //            添加资产调配的数据信息
            public static final String insertZcDeployData = "deploy/zc/insertZcDeployData";


            //            获取调配记录的列表
            public static final String deployRecordList = "deploy/deployRecordList";


            //            获取调配记录找到子记录数据
            public static final String listByZcDeployId = "deploy/listByZcDeployId";

        }

        public class Caigou {
            //管理部门列表
            public static final String caigou_guanlibumen = "appDept/glDeptList";

            //提交采购资产列表
            public static final String buy_insertData = "buy/insertData";

            //获取采购资产列表
            public static final String buyRecordList = "buy/buyRecordList";

            //采购列表详情
            public static final String getBuyRecordItemDetailList = "buy/getBuyRecordItemDetailList";
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

        public class Message {
            //获取所有代办消息列表
            public static final String getAllWailDealList = "api/getAllWailDealList";
            //            调配审核主信息
            public static final String deployCheckMainInfo = "deploy/deployCheckMainInfo";
            //            调配审核资产列表
            public static final String deployCheckItemList = "deploy/deployCheckItemList";
            //            资产调配审核
            public static final String deployCheck = "deploy/deployCheck";


            //            资产购买主信息
            public static final String buyCheckMainInfo = "buy/buyCheckMainInfo";
            //            购买待办列表信息
            public static final String buyCheckItemList = "buy/buyCheckItemList";
            //            购买审核
            public static final String buyCheck = "buy/buyCheck";

            //资产处置主信息
            public static final String bfCheckMainInfo = "deal/bfCheckMainInfo";
            //处置审核资产列表
            public static final String bfCheckItemList = "deal/bfCheckItemList";
            //            处置审核部门提交
            public static final String shbCheck = "deal/shbCheck";
            //提交到财务
            public static final String submitCw="deal/submitCw";
            //财务部审核
            public static final String cwCheck="deal/cwCheck";



            //            资产维修主信息
            public static final String repairCheckMainInfo = "app/repair/repairCheckMainInfo";


            //            资产维修审核列表信息
            public static final String repairCheckItemList = "app/repair/repairCheckItemList";


            //            维修审核
            public static final String repairCheck = "app/repair/repairCheck";


        }

        public static final String UploadImage = "files";
        //扫一扫
        public static final String getZcInfo = "api/getZcInfo";
    }


}
