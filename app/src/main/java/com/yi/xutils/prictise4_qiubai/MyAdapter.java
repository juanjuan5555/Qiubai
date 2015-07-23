package com.yi.xutils.prictise4_qiubai;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 15-7-22.
 */
public class MyAdapter extends BaseAdapter {

    private final BitmapDisplayConfig config;
    private Context context;
    private List<Content> list;

    public MyAdapter(Context context, List<Content> list) {
        this.context = context;
        this.list = list;

        config=new BitmapDisplayConfig();
        config.setBitmapFactory(new BitmapFactory() {
            /**
             * 创建自身的新的对象
             * 匿名内部类不能创建自身对象
             * @return
             */
            @Override
            public BitmapFactory cloneNew() {
                return this;
            }

            /**
             * 加工Bitmap
             * @param bitmap   原图
             * @return   加工后的图片
             * 可以再createBitmap这个方法中设置想要的图形
             */
            @Override
            public Bitmap createBitmap(Bitmap bitmap) {
                Bitmap ret=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),
                        Bitmap.Config.ARGB_8888);
                BitmapShader shader=new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                        Shader.TileMode.CLAMP);
                Paint paint=new Paint();
                paint.setShader(shader);
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.FILL);
                new Canvas(ret).drawCircle(bitmap.getWidth()/2,bitmap.getHeight()/2,
                        Math.min(bitmap.getWidth(),bitmap.getHeight())/2,paint);
                bitmap.recycle();
                bitmap=null;
                return ret;
            }
        });

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        Content content=list.get(position);
        holder.content.setText(content.getContent());
        if (content.getImage() == null) {
            holder.image.setVisibility(View.GONE);
        }else {
            holder.image.setVisibility(View.VISIBLE);
            BitmapHelper.getUtils().display(holder.image,content.getImageUrl());
        }
        if (content.getUser_name() == null) {
            holder.user.setVisibility(View.GONE);
        }else {
            holder.user.setVisibility(View.VISIBLE);
            holder.name.setText(content.getUser_name());
            BitmapHelper.getUtils().display(holder.icon,content.getIconUrl(),config);
        }
        return convertView;
    }

    public void addAll(Collection<? extends Content> contents){
        list.addAll(contents);
        notifyDataSetChanged();
    }

    public static class ViewHolder{
        @ViewInject(R.id.user_Layout)
        private LinearLayout user;
        @ViewInject(R.id.user_icon)
        private ImageView icon;
        @ViewInject(R.id.item_image)
        private ImageView image;
        @ViewInject(R.id.content)
        private TextView content;
        @ViewInject(R.id.user_name)
        private TextView name;
        public ViewHolder(View itemView) {
            ViewUtils.inject(this,itemView);

        }
    }
}
