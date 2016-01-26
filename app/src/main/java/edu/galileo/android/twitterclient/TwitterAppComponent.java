package edu.galileo.android.twitterclient;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ykro.
 */

@Singleton @Component(modules = {TwitterAppModule.class})
public interface TwitterAppComponent {
}

