package edu.galileo.android.twitterclient.hashtags.ui;

import java.util.List;

import edu.galileo.android.twitterclient.entities.Hashtag;

/**
 * Created by ykro.
 */
public interface HashtagsView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void onImagesError(String error);
    void setHashtags(List<Hashtag> items);
}
