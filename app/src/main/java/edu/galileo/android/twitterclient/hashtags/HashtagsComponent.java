package edu.galileo.android.twitterclient.hashtags;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ykro.
 */

@Singleton @Component(modules = {HashtagsModule.class})
public interface HashtagsComponent {
    void inject(HashtagsFragment fragment);
    //ImagesPresenter getPresenter();
}

