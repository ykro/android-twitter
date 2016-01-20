package edu.galileo.android.twitterclient.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by ykro.
 */
public class ImageLoading {
    Context context;

    public ImageLoading(Context context){
        this.context = context;
    }

    public void loadImage(String url, ImageView view) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .into(view);
    }

}
