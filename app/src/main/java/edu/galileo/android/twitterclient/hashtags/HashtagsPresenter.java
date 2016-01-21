package edu.galileo.android.twitterclient.hashtags;

/**
 * Created by ykro.
 */
public interface HashtagsPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashtagsEvent event);
}
