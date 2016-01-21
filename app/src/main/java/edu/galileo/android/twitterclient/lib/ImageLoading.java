package edu.galileo.android.twitterclient.lib;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by ykro.
 */
public class ImageLoading {
    Fragment fragment;

    public ImageLoading(Fragment fragment){
        this.fragment = fragment;
    }

    public void loadImage(String url, ImageView view) {
        Glide.with(fragment)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .override(600, 400)
                .into(view);
    }

}
