package com.yi.xutils.prictise4_qiubai;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.NotNull;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 15-7-22.
 */
@Table(name = "Content")
public class Content {
    @Column(column = "content")
    @NotNull
    @Unique
    private String content;
    @Id(column = "id")
    @NoAutoIncrement
    private int id;
    @Column(column = "image")
    private String image;
    @Column(column = "user_name")
    private String user_name;
    @Column(column = "user_icon")
    private String user_icon;
    @Column(column = "user_id")
    private int user_id;

    @JSONCreator
    public Content(@JSONField(name = "content") String content,
                   @JSONField(name = "id")int id,
                    @JSONField(name = "iamge")String image,
                   @JSONField(name = "user")JSONObject user) {
        this.content = content;
        this.id = id;
        this.image = image;
        if (user != null) {
            user_id=user.getInteger("id");
            user_name=user.getString("login");
            user_icon=user.getString("icon");
        }
    }

    public Content() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Content{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", image='" + image + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_icon='" + user_icon + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    public String getImageUrl(){
        Pattern pattern=Pattern.compile("(\\d+)\\d{4}");
        Matcher matcher=pattern.matcher(image);
        matcher.find();
        String url = String.format(UrlUtils.URL_IMAGE, matcher.group(1), matcher.group(0), "small", image);
        return url;
    }

    public String getIconUrl(){
        String url = String.format(UrlUtils.URL_USER_ICON, user_id / 10000, user_id, user_icon);
        return url;
    }
}
