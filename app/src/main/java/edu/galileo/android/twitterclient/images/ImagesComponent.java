package edu.galileo.android.twitterclient.images;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ykro.
 */

@Singleton @Component(modules = {ImagesModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);
    //ImagesPresenter getPresenter();
}

