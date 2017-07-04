package com.baway.hujiqinag1505b20170630;

import android.content.Context;
import android.os.AsyncTask;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import me.maxwin.view.XListView;

/**
 * 异步AsyncTask加网络请求实现数据展示
 * Created by 胡计强 on 2017/06/30.
 */
public class AsyncTaskUtil extends AsyncTask<Object, Void, String> implements XListView.IXListViewListener {
    //全局属性
    private List<Bean.ListBean> list;
    private Context context;
    private XListView xlv;
    private String path;
    private Map<String,Object> map;
    private MyAdapter adapter;
    private int page = 1;

    @Override
    protected String doInBackground(Object... params) {
        path = (String) params[0];
        xlv = (XListView) params[1];
        map = (Map) params[2];
        context = (Context) params[3];
        list = new ArrayList<>();
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        loadData(map);
        adapter = new MyAdapter(context, list);
        xlv.setPullLoadEnable(true);
        xlv.setPullRefreshEnable(true);
        xlv.setXListViewListener(this);
        xlv.setAdapter(adapter);
    }

    public void loadData(final Map<String,Object> map) {
        new Thread() {
            @Override
            public void run() {
                String result = UrlUtils.postUrlConnect(path, map);
                Gson gson = new Gson();
                Bean data = gson.fromJson(result, Bean.class);
                list.addAll(data.getList());
            }
        }.start();

    }

    public void stopXlist() {
        xlv.stopRefresh();
        xlv.stopLoadMore();
        xlv.setRefreshTime("刚刚");
    }

    //上拉刷新
    @Override
    public void onRefresh() {
        list.clear();
        loadData(map);
        adapter.notifyDataSetChanged();
        stopXlist();
    }

    //下拉加载时page++
    @Override
    public void onLoadMore() {
        page++;
        map.put("page", "" + page);
        loadData(map);
        adapter.notifyDataSetChanged();
        stopXlist();

    }
}
