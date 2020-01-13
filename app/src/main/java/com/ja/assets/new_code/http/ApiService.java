package com.ja.assets.new_code.http;

import com.ja.assets.new_code.Constants;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.ChuangjianpandiandanBean;
import com.ja.assets.new_code.bussiness.bean.post.DiaopeiZichanliiebiaoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DiaopeijiluPostBean;
import com.ja.assets.new_code.bussiness.bean.post.JiluXunjianPostBean;
import com.ja.assets.new_code.bussiness.bean.post.PandianZichanWanchengPostBean;
import com.ja.assets.new_code.bussiness.bean.post.PandianbaobiaoBasePostBean;
import com.ja.assets.new_code.bussiness.bean.post.PanyingAndkuiPostBean;
import com.ja.assets.new_code.bussiness.bean.post.TianxiexunjianPostBean;
import com.ja.assets.new_code.bussiness.bean.post.WeiPandianPostBean;
import com.ja.assets.new_code.bussiness.bean.post.WeiPandiianzichanPostBean;
import com.ja.assets.new_code.bussiness.bean.post.ZichanSaomaPostBean;
import com.ja.assets.new_code.bussiness.bean.result.BumenListBean;
import com.ja.assets.new_code.bussiness.bean.result.Diaopei_zichanliebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.DiaopeijiluBean;
import com.ja.assets.new_code.bussiness.bean.result.JiluXunjianDetail;
import com.ja.assets.new_code.bussiness.bean.result.PandianBaseResultBean;
import com.ja.assets.new_code.bussiness.bean.result.Pandian_zichanliebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.UploadImageResultBean;
import com.ja.assets.new_code.bussiness.bean.result.WeiPandianResultBean;
import com.ja.assets.new_code.bussiness.bean.result.YipandianResultBean;
import com.ja.assets.new_code.bussiness.bean.result.ZiChansBean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by long
 */
public interface ApiService {

    @POST(Constants.Url.Patrol.PatrolCheckList)
    Call<BaseBean<ArrayList<ZiChansBean>>> PatrolCheckList(@Header("token") String token);


    @POST(Constants.Url.Patrol.insertRecord)
    Call<BaseBean> insertRecord(@Header("token") String token, @Body TianxiexunjianPostBean bean);


    @POST(Constants.Url.Patrol.JiluPatrolCheckList)
    Call<BaseBean<ArrayList<ZiChansBean>>> JiluPatrolCheckList(@Header("token") String token);

    @POST(Constants.Url.Patrol.inspectDetail)
    Call<BaseBean<JiluXunjianDetail>> inspectDetail(@Header("token") String token, @Body JiluXunjianPostBean bean);


    @POST(Constants.Url.Inventory.getZcCheckList)
    Call<BaseBean<ArrayList<WeiPandianResultBean>>> getZcCheckList(@Header("token") String token, @Body WeiPandianPostBean bean);


    @POST(Constants.Url.Inventory.zcCheckSave)
    Call<BaseBean> zcCheckSave(@Header("token") String token, @Body ChuangjianpandiandanBean bean);

    @POST(Constants.Url.Inventory.zichanliebiao)
    Call<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>> zichanliebiao(@Header("token") String token, @Body WeiPandiianzichanPostBean bean);


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


    @POST(Constants.Url.DiaoPei.diaopei_bumenlist)
    Call<BaseBean<ArrayList<BumenListBean>>> diaopei_bumenlist(@Header("token") String token);



    @POST(Constants.Url.DiaoPei.insertZcDeployData)
    Call<BaseBean> insertZcDeployData(@Header("token") String token, @Body List<Diaopei_zichanliebiaoBean> bean);




    @POST(Constants.Url.DiaoPei.deployRecordList)
    Call<BaseBean<ArrayList<DiaopeijiluBean>>> deployRecordList(@Header("token") String token, @Body DiaopeijiluPostBean bean);


    //上传头像
    @Multipart
    @POST(Constants.Url.UploadImage)
    Call<UploadImageResultBean> uploadLogo(
            @Part MultipartBody.Part file, @Header("token") String token
    );
}