package edu.galileo.android.twitterclient.images;

/**
 * Created by ykro.
 */
public class ImagesInteractorImpl implements ImagesInteractor {
    private ImagesRepository imagesRepository;

    public ImagesInteractorImpl() {
        this.imagesRepository = ImagesRepository.getInstance();
    }

    @Override
    public void getImageItemsList() {
        this.imagesRepository.getImages();

    }
}
