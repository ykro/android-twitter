package edu.galileo.android.twitterclient.images;

import java.util.List;

import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.lib.EventBus;

/**
 * Created by ykro.
 */
public class ImagesPresenterImpl implements ImagesPresenter {
    private EventBus eventBus;
    private ImagesView imagesView;
    private final ImagesInteractor imagesInteractor;

    public ImagesPresenterImpl(ImagesView imagesView, ImagesInteractor imagesInteractor) {
        this.eventBus = EventBus.getInstance();

        this.imagesView = imagesView;
        this.imagesInteractor = imagesInteractor;
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.imagesView = null;
    }

    @Override
    public void getImageTweets(){
        if (this.imagesView != null){
            imagesView.hideList();
            imagesView.showProgress();
        }

        this.imagesInteractor.getImageItemsList();
    }

    @Override
    public void onEventMainThread(ImagesEvent event) {
        String errorMsg = event.getError();
        if (this.imagesView != null) {
            imagesView.showList();
            imagesView.hideProgress();
            if (errorMsg != null) {
                this.imagesView.onImagesError(errorMsg);
            } else {
                List<Image> items = event.getImages();
                if (items != null && !items.isEmpty()) {
                    this.imagesView.setImages(items);
                }
            }
        }
    }
}
