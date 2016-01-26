package edu.galileo.android.twitterclient.hashtags.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.twitterclient.TwitterAppModule;
import edu.galileo.android.twitterclient.hashtags.ui.HashtagsFragment;
import edu.galileo.android.twitterclient.lib.di.LibsModule;

/**
 * Created by ykro.
 */

@Singleton @Component(modules = {HashtagsModule.class, LibsModule.class, TwitterAppModule.class})
public interface HashtagsComponent {
    void inject(HashtagsFragment fragment);
}

