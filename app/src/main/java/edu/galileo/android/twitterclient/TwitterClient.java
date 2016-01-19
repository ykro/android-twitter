package edu.galileo.android.twitterclient;

import android.app.Application;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by ykro.
 */
public class TwitterClient extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("ASDF",BuildConfig.TWITTER_KEY);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }
}
