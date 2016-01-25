package edu.galileo.android.twitterclient;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.twitterclient.hashtags.HashtagsModule;

/**
 * Created by ykro.
 */

@Singleton @Component(modules = {TwitterAppModule.class})
public interface TwitterAppComponent {
}

