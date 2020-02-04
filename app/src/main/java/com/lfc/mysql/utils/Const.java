package com.lfc.mysql.utils;

/**
 * Created by LFC
 * on 2017/11/7.
 */

import android.Manifest;
import android.os.Environment;


import java.io.File;

/**
 * 常量
 */
public class Const {
    public static boolean ISDebug = false;  //是否是调试模式
    public static final int YZM_TIME = 60;  //验证码倒计时

    public static final int PW_Min_Length = 6;
    public static final int PW_Max_Length = 20;

    public static final int IMG_Width = 720;  //宽
    public static final int IMG_Height = 1280;  //高

    public static final long TimeBase = 621355968000000000L;  //时间计算
    //    传递intent   关键字
    public static final String WEBTYPE = "WebType";   // 跳转 web
    public static final String WEBTYPE_ID = "WEBID";   // 跳转 web
    public static final String TESTIMG_URL = "http://img1.3lian.com/2015/a1/105/d/40.jpg";   //


    //    public static final String OfficeUrl = "https://docs.google.com/gview?embedded=true&url=";   //
    public static final String OfficeUrl = "https://view.officeapps.live.com/op/view.aspx?src=";   //
    public static final String VideoUrl = "http://angliss.weiruanmeng.com/upload/default/20190819/b4a041bb0e887b4d766427464974ef80.mp4";   //
    public static final String Data_FileDirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "TextDB";
    //    public static final String Data_FileDirPath = Environment.getDataDirectory().getParentFile().getAbsolutePath() + File.separator + "DownloadApi";
    public static final String Data_FilePath = Data_FileDirPath;
    public static final String Data_Cache = Data_FileDirPath + File.separator + "ImgCache";
    public static final String Data_Download = Data_FileDirPath + File.separator + "ImgDownLoad";
    public static final String Data_ShareQRIMG = "myqr_img";
    public static final String Data_PhotoIMG = "photo.jpg";
    public static final String Data_LogoIMG = "logo";

    /**
     * 需要进行检测的权限数组
     */
    public static final String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };


    /**
     * 需要进行检测的权限数组
     */
    public static final String[] mapPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

}
