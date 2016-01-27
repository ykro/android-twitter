package edu.galileo.android.twitterclient.images.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.twitterclient.TwitterAppModule;
import edu.galileo.android.twitterclient.images.ui.ImagesFragment;
import edu.galileo.android.twitterclient.lib.di.LibsModule;

/**
 * Created by ykro.
 */

@Singleton @Component(modules = {ImagesModule.class, LibsModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);
    //ImagesPresenter getPresenter();
}

