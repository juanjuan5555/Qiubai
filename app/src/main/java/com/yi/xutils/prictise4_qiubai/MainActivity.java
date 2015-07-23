package com.yi.xutils.prictise4_qiubai;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.activity_main)
public class MainActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.listL)
    private ListView listView;
    private MyAdapter adapter;
    @ViewInject(R.id.refresh)
    private SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        BitmapHelper.init(this);
        DbHelper.init(this);
        List<Content> list=null;
        try {
            list=DbHelper.getUtils().findAll(Content.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (list == null) {
            list=new ArrayList<>();
        }


        adapter = new MyAdapter(this,list);
        listView.setAdapter(adapter);
        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);

    }


    //xUtils中没有refresh的绑定
    @Override
    public void onRefresh() {
        HttpUtils utils=new HttpUtils(10000);
        utils.send(HttpRequest.HttpMethod.GET, UrlUtils.URL_HOT_SUGGEST, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //进行解析
                JSONObject object= JSON.parseObject(responseInfo.result);
                List<Content> items = JSON.parseArray(object.getString("items"), Content.class);
                adapter.addAll(items);
                //注释掉try  catch才能看到下拉刷新效果
                try {
                    DbHelper.getUtils().saveOrUpdateAll(items);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                refresh.setRefreshing(false);
            }

            @Override
            public void onFailure(HttpException e, String s)
            {
                e.printStackTrace();
                refresh.setRefreshing(false);
            }
        });
    }
}
