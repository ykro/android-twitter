package edu.galileo.android.twitterclient.images.di;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.images.adapters.ImagesAdapter;
import edu.galileo.android.twitterclient.images.ImagesInteractor;
import edu.galileo.android.twitterclient.images.ImagesInteractorImpl;
import edu.galileo.android.twitterclient.images.ImagesPresenter;
import edu.galileo.android.twitterclient.images.ImagesPresenterImpl;
import edu.galileo.android.twitterclient.images.ImagesRepository;
import edu.galileo.android.twitterclient.images.ImagesRepositoryImpl;
import edu.galileo.android.twitterclient.images.ui.ImagesView;
import edu.galileo.android.twitterclient.images.ui.OnItemClickListener;
import edu.galileo.android.twitterclient.lib.EventBus;
import edu.galileo.android.twitterclient.lib.ImageLoading;

/**
 * Created by ykro.
 */
@Module
public class ImagesModule {
    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    List<Image> provideItems() {
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    OnItemClickListener provideClickListener() {
        return this.clickListener;
    }

    @Provides
    ImagesAdapter provideAdapter(List<Image> items, OnItemClickListener clickListener, ImageLoading imageLoadingHelper) {
        return new ImagesAdapter(items, clickListener, imageLoadingHelper);
    }

    @Provides
    @Singleton
    ImagesView provideImagesView() {
        return this.view;
    }

    @Provides
    @Singleton
    ImagesPresenter provideImagesPresenter(ImagesView view, ImagesInteractor interactor, EventBus eventBus) {
        return new ImagesPresenterImpl(view, interactor, eventBus);
    }

    @Provides
    @Singleton
    ImagesInteractor provideImagesInteractor(ImagesRepository repository) {
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository provideImagesRepository(CustomTwitterApiClient client, EventBus eventBus) {
        return new ImagesRepositoryImpl(client, eventBus);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient provideTwitterApiClient(TwitterSession session) {
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession provideTwitterSession() {
        return Twitter.getSessionManager().getActiveSession();
    }
}
