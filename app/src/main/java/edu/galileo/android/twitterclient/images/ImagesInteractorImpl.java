package edu.galileo.android.twitterclient.images;

import edu.galileo.android.twitterclient.api.ImagesAPI;

/**
 * Created by ykro.
 */
public class ImagesInteractorImpl implements ImagesInteractor {
    private ImagesAPI imagesAPI;

    public ImagesInteractorImpl() {
        this.imagesAPI = ImagesAPI.getInstance();
    }

    @Override
    public void getImageItemsList() {
        this.imagesAPI.getImages();

    }
}
