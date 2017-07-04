package com.baway.hujiqinag1505b20170630;


import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by 胡计强 on 2017/06/30.
 */
public class BaseBannerImageLoader extends com.youth.banner.loader.ImageLoader {

    private final ImageLoader imageLoader;
    private final DisplayImageOptions options;

    public BaseBannerImageLoader(Context context) {
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(context);
        //将configuration配置到imageloader中
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(configuration);

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.ARGB_8888)//设置图片的解码类型
                .showImageOnLoading(R.mipmap.ic_launcher)//设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)//设置图片加载/解码过程中错误时候显示的图片
                .build();
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageLoader.displayImage(path.toString(), imageView, options);
    }
}
