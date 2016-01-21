package edu.galileo.android.twitterclient.images;

import edu.galileo.android.twitterclient.events.ImageEvent;

/**
 * Created by ykro.
 */
public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onEventMainThread(ImageEvent event);
}
