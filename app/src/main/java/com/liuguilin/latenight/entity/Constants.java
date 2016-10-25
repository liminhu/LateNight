package com.liuguilin.latenight.entity;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.entity
 *  文件名:   Constants
 *  创建者:   LGL
 *  创建时间:  2016/10/18 16:28
 *  描述：    常量&接口&方法&字段
 */

import android.content.Context;
import android.graphics.Paint;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.widget.TextView;

public class Constants {


    //搜索API
    //category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
    //count 最大 50
    public static final String GANK_SEARCH = "http://gank.io/api/search/query/listview/category/Android/count/10/page/1 ";
    //每日更新API
    public static final String GANK_UPDATE_DAILY = "http://gank.io/api/data/Android/10/1";
    //获取某几日干货网站数据API
    public static final String GANK_FAW_DAY = "http://gank.io/api/history/content/2/1";
    //获取特定日期网站数据API
    public static final String GANK_SPECIFIC_DAY = "http://gank.io/api/history/content/day/2016/05/11";
    //获取发过干货日期API
    //GET
    public static final String GANK_SEND_DATA_LSIT = "http://gank.io/api/day/history";
    //支持提交干货到审核区API
    //POST
    public static final String GANK_POST_DATA = "https://gank.io/api/add2gank";

    //Bmob key
    public static final String BMOB_KEY = "c478860d32aa382ad179d59eec6049fc";
    //Bugly key
    public static final String BUGLY_KEY = "bd467eaf8d";


    //延时启动
    public static final int HANDLER_WHAT_IS_FIRST = 10001;
    //倒计时
    public static final int HANDLER_WHAT_TIME_DOWN = 10002;


    //第一次运行
    public static final String SHARE_IS_FIRST = "isFirst";
    //第一次登录
    public static final String SHARE_IS_FIRST_LOGIN = "isFirstLogin";
    //自动登录
    public static final String SHARE_AUTO_LOGIN = "autoLogin";

	//密码正则
	public static final String PASSWORD_KEY = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

    //设置下划线
    public static void setHtml(TextView textView) {
        //设置下划线
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //抗锯齿
        textView.getPaint().setAntiAlias(true);
    }

    //判断网络是否有用
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
    //判断内存卡是否存在
    public static boolean isSdCardExist() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    private static int mStreamVolume = 0;

    // 设置到某个音量值--vain
    public static int setVolume(Context context, int volumeValue) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(AudioManager.STREAM_MUSIC, volumeValue, 0);
        // 返回当前媒体音量
        return am.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    // 音量加减----vain
    public static int RaiseOrLowerVolume(Context context, boolean isAdd, int volumeValue) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (isAdd) {
            mStreamVolume += volumeValue;
            if (mStreamVolume >= am.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) {
                mStreamVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            }
            am.setStreamVolume(AudioManager.STREAM_MUSIC, mStreamVolume, 0);
        } else {
            mStreamVolume -= volumeValue;
            if (mStreamVolume <= 0) {
                mStreamVolume = 0;
            }
            am.setStreamVolume(AudioManager.STREAM_MUSIC, mStreamVolume, 0);
        }
        // 返回当前媒体音量
        return am.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    //设置静音
    public static void setMute(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) != 0) {
            mStreamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        }
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
    }

    //取消静音
    public static void setUnMute(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        // 隐藏音乐进度条
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (mStreamVolume != 0) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mStreamVolume, 0);
        } else {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        }
    }

}