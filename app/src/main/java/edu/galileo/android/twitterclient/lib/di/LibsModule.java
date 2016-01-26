package edu.galileo.android.twitterclient.lib.di;

import android.support.v4.app.Fragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.lib.EventBus;
import edu.galileo.android.twitterclient.lib.ImageLoading;

/**
 * Created by ykro.
 */
@Module
public class LibsModule {
    private Fragment fragment;

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
