package edu.galileo.android.twitterclient.images;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.twitterclient.lib.LibsModule;

/**
 * Created by ykro.
 */

@Singleton @Component(modules = {ImagesModule.class, LibsModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);
    //ImagesPresenter getPresenter();
}

