package edu.galileo.android.twitterclient.hashtags;

import edu.galileo.android.twitterclient.events.HashtagEvent;

/**
 * Created by ykro.
 */
public interface HashtagsPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashtagEvent event);
}
