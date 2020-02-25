package com.ja.assets.new_code.http;

import com.ja.assets.new_code.Constants;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.BaoXiuZichanliiebiaoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.BaoxiujiluPostBean;
import com.ja.assets.new_code.bussiness.bean.post.BuyCheckItemListPostBean;
import com.ja.assets.new_code.bussiness.bean.post.BuyCheckMainInfoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.BuyCheckPostBean;
import com.ja.assets.new_code.bussiness.bean.post.CaigouiLiebiaoPostbean;
import com.ja.assets.new_code.bussiness.bean.post.Caigouitemzichan;
import com.ja.assets.new_code.bussiness.bean.post.CaigouxiangqingPostBean;
import com.ja.assets.new_code.bussiness.bean.post.CaigouzichanPostBean;
import com.ja.assets.new_code.bussiness.bean.post.ChuangjianpandiandanBean;
import com.ja.assets.new_code.bussiness.bean.post.ChuzhiLiebiaoPostbean;
import com.ja.assets.new_code.bussiness.bean.post.ChuzhiZichanliiebiaoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.ChuzhishenqingPostBean;
import com.ja.assets.new_code.bussiness.bean.post.ChuzhizichanPostbean;
import com.ja.assets.new_code.bussiness.bean.post.DeployCheckItemListPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DeployCheckMainInfoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DeployCheckPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DiaopeiZichanliiebiaoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DiaopeijiluPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DiaopeixiangqingPostBean;
import com.ja.assets.new_code.bussiness.bean.post.JiluXunjianPostBean;
import com.ja.assets.new_code.bussiness.bean.post.PandianZichanWanchengPostBean;
import com.ja.assets.new_code.bussiness.bean.post.PandianbaobiaoBasePostBean;
import com.ja.assets.new_code.bussiness.bean.post.PanyingAndkuiPostBean;
import com.ja.assets.new_code.bussiness.bean.post.RepairCheckItemListPostBean;
import com.ja.assets.new_code.bussiness.bean.post.RepairCheckMainInfoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.RepairCheckPostBean;
import com.ja.assets.new_code.bussiness.bean.post.TianxiexunjianPostBean;
import com.ja.assets.new_code.bussiness.bean.post.UpdatePasswordPostBean;
import com.ja.assets.new_code.bussiness.bean.post.WeiPandianPostBean;
import com.ja.assets.new_code.bussiness.bean.post.WeiPandiianzichanPostBean;
import com.ja.assets.new_code.bussiness.bean.post.ZichanSaomaPostBean;
import com.ja.assets.new_code.bussiness.bean.result.BaoxiuDetailBean;
import com.ja.assets.new_code.bussiness.bean.result.BaoxiuijilulistBean;
import com.ja.assets.new_code.bussiness.bean.result.Biaoxiiu_zichanliebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.BumenListBean;
import com.ja.assets.new_code.bussiness.bean.result.BuyCheckItemListResultBean;
import com.ja.assets.new_code.bussiness.bean.result.BuyCheckMainInfoResultBean;
import com.ja.assets.new_code.bussiness.bean.result.CaigoulistResultBean;
import com.ja.assets.new_code.bussiness.bean.result.Chuzhi_zichanliebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.Chuzhi_zichanliiebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.ChuzhiliiebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.DaibanMessageResultBean;
import com.ja.assets.new_code.bussiness.bean.result.DeployCheckItemListResultBean;
import com.ja.assets.new_code.bussiness.bean.result.DeployCheckMainInfoResultBean;
import com.ja.assets.new_code.bussiness.bean.result.Diaopei_zichanliebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.DiaopeijiluBean;
import com.ja.assets.new_code.bussiness.bean.result.DiaopeijiluxiangqingBean;
import com.ja.assets.new_code.bussiness.bean.result.GuanliBumenListBean;
import com.ja.assets.new_code.bussiness.bean.result.JiluXunjianDetail;
import com.ja.assets.new_code.bussiness.bean.result.PandianBaseResultBean;
import com.ja.assets.new_code.bussiness.bean.result.Pandian_zichanliebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.RepairCheckItemListResultBean;
import com.ja.assets.new_code.bussiness.bean.result.RepairCheckMainInfoResultBean;
import com.ja.assets.new_code.bussiness.bean.result.UploadImageResultBean;
import com.ja.assets.new_code.bussiness.bean.result.WeiPandianResultBean;
import com.ja.assets.new_code.bussiness.bean.result.YipandianResultBean;
import com.ja.assets.new_code.bussiness.bean.result.ZiChansBean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by long
 */
public interface ApiService {

    @POST(Constants.Url.Message.getAllWailDealList)
    Call<BaseBean<ArrayList<DaibanMessageResultBean>>> getAllWailDealList(@Header("token") String token);

    @POST(Constants.Url.Message.deployCheckMainInfo)
    Call<BaseBean<DeployCheckMainInfoResultBean>> deployCheckMainInfo(@Header("token") String token, @Body DeployCheckMainInfoPostBean bean);


    @POST(Constants.Url.Message.buyCheckMainInfo)
    Call<BaseBean<BuyCheckMainInfoResultBean>> buyCheckMainInfo(@Header("token") String token, @Body BuyCheckMainInfoPostBean bean);


    @POST(Constants.Url.Message.repairCheckMainInfo)
    Call<BaseBean<RepairCheckMainInfoResultBean>> repairCheckMainInfo(@Header("token") String token, @Body RepairCheckMainInfoPostBean bean);

    @POST(Constants.Url.Message.deployCheckItemList)
    Call<BaseBean<ArrayList<DeployCheckItemListResultBean>>> deployCheckItemList(@Header("token") String token, @Body DeployCheckItemListPostBean bean);


    @POST(Constants.Url.Message.buyCheckItemList)
    Call<BaseBean<ArrayList<BuyCheckItemListResultBean>>> buyCheckItemList(@Header("token") String token, @Body BuyCheckItemListPostBean bean);





    @POST(Constants.Url.Message.repairCheckItemList)
    Call<BaseBean<ArrayList<RepairCheckItemListResultBean>>>  repairCheckItemList(@Header("token") String token, @Body RepairCheckItemListPostBean bean);

    @POST(Constants.Url.Message.deployCheck)
    Call<BaseBean> deployCheck(@Header("token") String token, @Body DeployCheckPostBean bean);


    @POST(Constants.Url.Message.buyCheck)
    Call<BaseBean> buyCheck(@Header("token") String token, @Body BuyCheckPostBean bean);


    @POST(Constants.Url.Message.repairCheck)
    Call<BaseBean> repairCheck(@Header("token") String token, @Body RepairCheckPostBean bean);



    @POST(Constants.Url.Me.updatePassword)
    Call<BaseBean> updatePassword(@Header("token") String token, @Body UpdatePasswordPostBean bean);

    @POST(Constants.Url.Patrol.PatrolCheckList)
    Call<BaseBean<ArrayList<ZiChansBean>>> PatrolCheckList(@Header("token") String token);


    @POST(Constants.Url.Patrol.insertRecord)
    Call<BaseBean> insertRecord(@Header("token") String token, @Body TianxiexunjianPostBean bean);


    @POST(Constants.Url.Patrol.JiluPatrolCheckList)
    Call<BaseBean<ArrayList<ZiChansBean>>> JiluPatrolCheckList(@Header("token") String token);


    @POST(Constants.Url.BaoXiu.repairRecordList)
    Call<BaseBean<ArrayList<BaoxiuijilulistBean>>> repairRecordList(@Header("token") String token, @Body BaoxiujiluPostBean bean);

    @POST(Constants.Url.Patrol.inspectDetail)
    Call<BaseBean<JiluXunjianDetail>> inspectDetail(@Header("token") String token, @Body JiluXunjianPostBean bean);


    @POST(Constants.Url.BaoXiu.listByZcReId)
    Call<BaseBean<ArrayList<BaoxiuDetailBean>>> listByZcReId(@Header("token") String token, @Body JiluXunjianPostBean bean);


    @POST(Constants.Url.Inventory.getZcCheckList)
    Call<BaseBean<ArrayList<WeiPandianResultBean>>> getZcCheckList(@Header("token") String token, @Body WeiPandianPostBean bean);


    @POST(Constants.Url.Inventory.zcCheckSave)
    Call<BaseBean> zcCheckSave(@Header("token") String token, @Body ChuangjianpandiandanBean bean);

    @POST(Constants.Url.Inventory.zichanliebiao)
    Call<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>> zichanliebiao(@Header("token") String token, @Body WeiPandiianzichanPostBean bean);


    @POST(Constants.Url.ChuZhi.getBFRecordItemList)
    Call<BaseBean<ArrayList<Chuzhi_zichanliiebiaoBean>>> getBFRecordItemList(@Header("token") String token, @Body ChuzhizichanPostbean bean);


    @POST(Constants.Url.Inventory.updateZcItemStatus)
    Call<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>> updateZcItemStatus(@Header("token") String token, @Body ZichanSaomaPostBean bean);


    @POST(Constants.Url.Inventory.finishAssetsStatus)
    Call<BaseBean> finishAssetsStatus(@Header("token") String token, @Body PandianZichanWanchengPostBean bean);

    @POST(Constants.Url.Inventory.checkRecordList)
    Call<BaseBean<ArrayList<YipandianResultBean>>> checkRecordList(@Header("token") String token, @Body WeiPandianPostBean bean);

    @POST(Constants.Url.Inventory.checkReport_base)
    Call<BaseBean<PandianBaseResultBean>> checkReport_base(@Header("token") String token, @Body PandianbaobiaoBasePostBean bean);

    @POST(Constants.Url.Inventory.profitList)
    Call<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>> profitList(@Header("token") String token, @Body PanyingAndkuiPostBean bean);


    @POST(Constants.Url.DiaoPei.diaopei_zichanliebiao)
    Call<BaseBean<ArrayList<Diaopei_zichanliebiaoBean>>> diaopei_zichanliebiao(@Header("token") String token, @Body DiaopeiZichanliiebiaoPostBean bean);


    @GET(Constants.Url.DiaoPei.diaopei_bumenlist)
    Call<BaseBean<ArrayList<BumenListBean>>> diaopei_bumenlist(@Header("token") String token);


    @GET(Constants.Url.Caigou.caigou_guanlibumen)
    Call<BaseBean<ArrayList<GuanliBumenListBean>>> caigou_guanlibumen(@Header("token") String token);


    @POST(Constants.Url.Caigou.buy_insertData)
    Call<BaseBean> buy_insertData(@Header("token") String token, @Body CaigouzichanPostBean bean);


    @POST(Constants.Url.Caigou.buyRecordList)
    Call<BaseBean<ArrayList<CaigoulistResultBean>>> buyRecordList(@Header("token") String token, @Body CaigouiLiebiaoPostbean bean);

    @POST(Constants.Url.Caigou.getBuyRecordItemDetailList)
    Call<BaseBean<ArrayList<Caigouitemzichan>>> getBuyRecordItemDetailList(@Header("token") String token, @Body CaigouxiangqingPostBean bean);

    @POST(Constants.Url.BaoXiu.repairList)
    Call<BaseBean<ArrayList<Biaoxiiu_zichanliebiaoBean>>> repairList(@Header("token") String token, @Body BaoXiuZichanliiebiaoPostBean bean);

    @POST(Constants.Url.ChuZhi.dealBfList)
    Call<BaseBean<ArrayList<Chuzhi_zichanliebiaoBean>>> dealBfList(@Header("token") String token, @Body ChuzhiZichanliiebiaoPostBean bean);


    @POST(Constants.Url.DiaoPei.insertZcDeployData)
    Call<BaseBean> insertZcDeployData(@Header("token") String token, @Body List<Diaopei_zichanliebiaoBean> bean);


    @POST(Constants.Url.BaoXiu.insertRepairData)
    Call<BaseBean> insertRepairData(@Header("token") String token, @Body List<Biaoxiiu_zichanliebiaoBean> bean);

    @POST(Constants.Url.ChuZhi.insertBfData)
    Call<BaseBean> insertBfData(@Header("token") String token, @Body ChuzhishenqingPostBean bean);

    @POST(Constants.Url.DiaoPei.deployRecordList)
    Call<BaseBean<ArrayList<DiaopeijiluBean>>> deployRecordList(@Header("token") String token, @Body DiaopeijiluPostBean bean);


    @POST(Constants.Url.ChuZhi.getBFRecordList)
    Call<BaseBean<ArrayList<ChuzhiliiebiaoBean>>> getBFRecordList(@Header("token") String token, @Body ChuzhiLiebiaoPostbean bean);


    @POST(Constants.Url.DiaoPei.listByZcDeployId)
    Call<BaseBean<ArrayList<DiaopeijiluxiangqingBean>>> listByZcDeployId(@Header("token") String token, @Body DiaopeixiangqingPostBean bean);


    //上传头像
    @Multipart
    @POST(Constants.Url.UploadImage)
    Call<UploadImageResultBean> uploadLogo(
            @Part MultipartBody.Part file, @Header("token") String token
    );
}