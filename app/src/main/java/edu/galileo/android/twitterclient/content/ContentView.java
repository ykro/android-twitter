package edu.galileo.android.twitterclient.content;

import java.util.List;

import edu.galileo.android.twitterclient.entities.TweetEntity;

/**
 * Created by ykro.
 */
public interface ContentView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void onImagesError(String error);
    void setItems(List<TweetEntity> items);
}
