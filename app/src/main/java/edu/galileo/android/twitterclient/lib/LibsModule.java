package edu.galileo.android.twitterclient.lib;

import android.support.v4.app.Fragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ykro.
 */
@Module
public class LibsModule {
    Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return new EventBus();
    }

    @Provides
    @Singleton
    ImageLoading provideImageLoading(Fragment fragment) {
        return new ImageLoading(fragment);
    }

    @Provides
    @Singleton
    Fragment provideImageLoadingFragment() {
        return this.fragment;
    }
}
