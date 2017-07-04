package com.baway.hujiqinag1505b20170630;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.maxwin.view.XListView;

/**
 * 主页面类
 * 传入请求路径跟控件
 * Created by 胡计强 on 2017/06/30.
 */
public class MainActivity extends AppCompatActivity {
    //全局变量
    private XListView xlv;
    private String path = "http://qhb.2dyt.com/Bwei/news";
    private View view;
    private Banner banner;
    private Map<String,Object> map;
    private List<Bean.ListBean> list = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Gson gson = new Gson();
            Bean data = gson.fromJson(msg.obj.toString(), Bean.class);
            list.addAll(data.getList());
            List<String> imageList = new ArrayList<>();
            for (Bean.ListBean listBean : list) {
                imageList.add(listBean.getPic());

            }
            banner.setImages(imageList);
            banner.start();
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isNetworkAvailable(this)) {
            load();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setMessage("没有网络，是否开启wifi？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            dialog.dismiss();
                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            startActivity(intent);
                            dialog.dismiss();
                            load();
                        }

                    });
            builder.create().show();
        }
    }

    public void load() {
        map = new HashMap<String,Object>();
        map.put("page", "1");
        map.put("type", "6");
        map.put("postkey", "1503d");
        xlv = (XListView) findViewById(R.id.myxlist);
        view = View.inflate(this, R.layout.list_head, null);
        loadBanner();
        xlv.addHeaderView(view);
        AsyncTaskUtil task = new AsyncTaskUtil();
        task.execute(path, xlv, map, this);

    }

    public void loadBanner() {
        banner = (Banner) view.findViewById(R.id.banner);
        //设置图片加载
        banner.setImageLoader(new BaseBannerImageLoader(this));
        //设置显示样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setBannerAnimation(Transformer.DepthPage);
        loadData();
    }

    public void loadData() {
        new Thread() {
            @Override
            public void run() {
                String result = UrlUtils.postUrlConnect(path, map);
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();

    }

    //判断当前有无网络连接
       public boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo info = manager.getActiveNetworkInfo();
                if (info != null) {
                    return info.isAvailable();
                }
            }
        }
        return false;
    }
}
