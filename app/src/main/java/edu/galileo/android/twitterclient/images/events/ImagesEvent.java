package edu.galileo.android.twitterclient.images.events;

import java.util.List;

import edu.galileo.android.twitterclient.entities.Image;

/**
 * Created by ykro.
 */
public class ImagesEvent {
    private String error;
    private List<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
