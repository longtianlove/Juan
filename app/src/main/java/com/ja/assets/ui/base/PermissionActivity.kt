package com.ja.assets.ui.base

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import kr.co.namee.permissiongen.PermissionFail
import kr.co.namee.permissiongen.PermissionGen
import kr.co.namee.permissiongen.PermissionSuccess


/**
 * Created by zry on 2018/2/7.
 */
abstract class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getTakePicPermission()
    }


    //申请拍照和文件读写权限
    private fun getTakePicPermission() {

        PermissionGen.with(this)
                .addRequestCode(1000)
                .permissions(
                        //位置
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        //相机、麦克风
                        android.Manifest.permission.RECORD_AUDIO,
                        android.Manifest.permission.WAKE_LOCK,
                        android.Manifest.permission.CAMERA,
                        //存储空间SD卡读写权限
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_SETTINGS,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        //读取手机状态
                        android.Manifest.permission.ACCESS_WIFI_STATE,
                        android.Manifest.permission.ACCESS_NETWORK_STATE,
                        android.Manifest.permission.MODIFY_AUDIO_SETTINGS,
                        android.Manifest.permission.CALL_PHONE
                )
                .request()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }

    @PermissionSuccess(requestCode = 1000)
    fun requestPhotoSuccess() {
        //成功之后不做处理
    }

    @PermissionFail(requestCode = 1000)
    fun requestPhotoFail() {
        //失败之后的处理，我一般是跳到设置界面
        goToSetting(this)
    }

    /**
     * 去设置界面
     */
    fun goToSetting(context: Context) {
        //go to setting view
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        context.startActivity(intent)

    }
}