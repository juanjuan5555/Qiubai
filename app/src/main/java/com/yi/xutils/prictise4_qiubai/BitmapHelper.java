package com.yi.xutils.prictise4_qiubai;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;

/**
 * Created by Administrator on 15-7-22.
 */
public class BitmapHelper {
    private static BitmapUtils utils;
    public  static void init(Context context){
        //第二个参数为：图片下载的地方，为null为cash文件
        utils=new BitmapUtils(context,null,0.25f,10<<20);
        utils.configDefaultLoadingImage(R.mipmap.ic_launcher);
        utils.configDefaultLoadFailedImage(R.drawable.abc_btn_default_mtrl_shape);
        utils.configThreadPoolSize(5);
//        BitmapDisplayConfig config=new BitmapDisplayConfig();
//        config.setBitmapFactory(new BitmapFactory() {
//            /**
//             * 创建自身的新的对象
//             * 匿名内部类不能创建自身对象
//             * @return
//             */
//            @Override
//            public BitmapFactory cloneNew() {
//                return this;
//            }
//
//            /**
//             * 加工Bitmap
//             * @param bitmap   原图
//             * @return 加工后的图片
//             */
//            @Override
//            public Bitmap createBitmap(Bitmap bitmap) {
//                Bitmap ret = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
//                        Bitmap.Config.ARGB_8888);
//                BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
//                        Shader.TileMode.CLAMP);
//                Paint paint = new Paint();
//                paint.setShader(shader);
//                paint.setAntiAlias(true);
//                paint.setStyle(Paint.Style.FILL);
//                new Canvas(ret).drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
//                        Math.min(bitmap.getWidth(), bitmap.getHeight()) / 2, paint);
//                bitmap.recycle();
//                bitmap = null;
//                return ret;
//            }
//        });
//        utils.configDefaultDisplayConfig(config);//默认显示设置

    }

    public static BitmapUtils getUtils() {
        return utils;
    }
}
