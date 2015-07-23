package com.yi.xutils.prictise4_qiubai;

import android.content.Context;

import com.lidroid.xutils.DbUtils;

/**
 * Created by Administrator on 15-7-22.
 */
public class DbHelper {
    public static DbUtils utils;
    public static void init(Context context){
        utils=DbUtils.create(context);
        //开启事务
        utils.configAllowTransaction(true);
        //开启调试模式
        utils.configDebug(true);
    }

    public static DbUtils getUtils() {
        return utils;
    }
}
