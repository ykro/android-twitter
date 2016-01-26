package edu.galileo.android.twitterclient.images.ui;

import java.util.List;

import edu.galileo.android.twitterclient.images.entities.Image;


/**
 * Created by ykro.
 */
public interface ImagesView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void onImagesError(String error);
    void setImages(List<Image> items);
}
