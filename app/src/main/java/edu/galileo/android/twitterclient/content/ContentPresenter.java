package edu.galileo.android.twitterclient.content;

import edu.galileo.android.twitterclient.events.TweetEvent;

/**
 * Created by ykro.
 */
public interface ContentPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getTweets();
    void onEventMainThread(TweetEvent event);
}
