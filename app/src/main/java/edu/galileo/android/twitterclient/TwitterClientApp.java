package edu.galileo.android.twitterclient;

import android.app.Application;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by ykro.
 */
public class TwitterClientApp extends Application {
    TwitterAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        initFabric();
        //initInjector();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    private void initInjector() {
        component = DaggerTwitterAppComponent
                    .builder()
                    .twitterAppModule(new TwitterAppModule(this))
                    .build();
    }

    public TwitterAppComponent getComponent() {
        return component;
    }
}
