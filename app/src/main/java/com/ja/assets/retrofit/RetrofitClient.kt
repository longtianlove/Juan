package com.ja.assets.retrofit

import android.util.Log
import com.ja.assets.config.MyApplication
import com.tsy.sdk.myokhttp.MyOkHttp
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/**
 * @创建者 ray@wdzj.com
 * @描述 配置Retrofit（配置网络缓存cache、配置持久cookie免登录）
 */

object   RetrofitClient {



    //构造一个线程池
     val threadPool: ThreadPoolExecutor = ThreadPoolExecutor(
        1,
        1,
        10,
        TimeUnit.SECONDS,
        ArrayBlockingQueue<Runnable>(1),
        ThreadPoolExecutor.DiscardOldestPolicy())

    //本地部署固定资产网址
//    const val BASE_URL = "http://123.56.66.231:8080/"

//    const val BASE_URL = "http://10.0.26.42:8080/"
//const val BASE_URL = "http://10.0.26.3:8080/"
const val BASE_URL = "http://testyq.17sys.cn/"

    /**
     * 图片加载
     */
    const  val UploadImg = "$BASE_URL/UpLoads/"

    /**
     * app下载地址
     */
    const   val downloadApp = "$BASE_URL/Service/DownloadDoctorApp"

    /**
     * app名称
     */
    const val APK_NAME = "固定资产盘点.APK"




//
//    init {
//        retrofit = Retrofit.Builder()
//            .client(getOkHttpClient())
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//    }
//
//    private fun getOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//                .addInterceptor(getHttpLogging())
//                .cookieJar(MyApplication.instance().cookieJar!!)
//                .connectTimeout(100, TimeUnit.SECONDS)
//                .readTimeout(100, TimeUnit.SECONDS)
//                .writeTimeout(100, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true).build()
//    }
//
//    fun <T> create(service: Class<T>?): T =
//        retrofit.create(service!!) ?: throw RuntimeException("Api service is null!")
//
//
//


    val  myOkHttp:MyOkHttp=MyOkHttp()




    private fun getHttpLogging(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.e("RetrofitLog", "retrofitBack=$message")
            }
        })
        httpLoggingInterceptor.apply { httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY }
        return httpLoggingInterceptor
    }

    @JvmField
    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(getHttpLogging())
        .cookieJar(MyApplication.instance().cookieJar!!)
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true).build()


    /**
     * 使用RxJava请求网络
     */
    @JvmField
    val retrofit: ApiDynamic = Retrofit.Builder()
        // 设置 网络请求 Url
        .baseUrl(BASE_URL)
        //设置使用Gson解析(记得加入依赖)
        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(okHttpClient)
        .build().create(ApiDynamic::class.java)


    /**
     * 使用kotlin的协成请求网络
     */
    @JvmField
    val networkService: ApiDynamic = Retrofit.Builder()
        // 设置 网络请求 Url
        .baseUrl(BASE_URL)
        //设置使用Gson解析(记得加入依赖)
        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build().create(ApiDynamic::class.java)
}
