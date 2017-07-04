package com.baway.hujiqinag1505b20170630;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * xListview控件的适配器
 * 先写xlistview控件适配器，再用imageloader 显示图片
 * Created by 胡计强 on 2017/06/30.
 */
public class MyAdapter extends BaseAdapter {
    //全局变量
    private Context context;
    private List<Bean.ListBean> list;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    private List<String> imageList = new ArrayList<>();
    private final ImageLoader imageLoader;
    private final DisplayImageOptions options1;

    //适配器方法
    public MyAdapter(Context context, List<Bean.ListBean> list) {
        this.context = context;
        this.list = list;
        for (Bean.ListBean listBean : list) {
            imageList.add(listBean.getPic());
        }
        File file = StorageUtils.getOwnCacheDirectory(context, "yhdl");
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context).diskCache(new UnlimitedDiskCache(file)).build();
        //将configuration配置到imageloader中
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(configuration);

        options1 = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.ARGB_8888)//设置图片的解码类型
                .showImageOnLoading(R.mipmap.ic_launcher)//设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)//设置图片加载/解码过程中错误时候显示的图片
                .build();
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
    public int getItemViewType(int position) {
        return list.get(position).getType() == 1 ? TYPE_1 : TYPE_2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new ViewHolder();
            if (type == TYPE_1) {
                convertView = View.inflate(context, R.layout.item1, null);
                holder.title = (TextView) convertView.findViewById(R.id.textview1);
                holder.imageView1 = (ImageView) convertView.findViewById(R.id.image1);
            }
            if (type == TYPE_2) {
                convertView = View.inflate(context, R.layout.item2, null);
                holder.title = (TextView) convertView.findViewById(R.id.textview2);
                holder.imageView1 = (ImageView) convertView.findViewById(R.id.image1);
                holder.imageView2 = (ImageView) convertView.findViewById(R.id.image2);
                holder.imageView3 = (ImageView) convertView.findViewById(R.id.image3);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bean.ListBean bean = list.get(position);
        if (type == TYPE_1) {
            holder.title.setText(bean.getTitle());
            imageLoader.displayImage(bean.getPic(), holder.imageView1, options1);
        }
        if (type == TYPE_2) {
            String[] images = bean.getPic().split("\\|");
            holder.title.setText(bean.getTitle());
            imageLoader.displayImage(images[0], holder.imageView1, options1);
            imageLoader.displayImage(images[1], holder.imageView2, options1);
            imageLoader.displayImage(images[2], holder.imageView3, options1);
        }
        return convertView;
    }

    class ViewHolder {
        TextView title;
        ImageView imageView1, imageView2, imageView3;

    }
}
