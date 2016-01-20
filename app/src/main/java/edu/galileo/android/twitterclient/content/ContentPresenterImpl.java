package edu.galileo.android.twitterclient.content;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.List;

import edu.galileo.android.twitterclient.entities.TweetModel;
import edu.galileo.android.twitterclient.events.TweetEvent;
import edu.galileo.android.twitterclient.lib.EventBus;

/**
 * Created by ykro.
 */
public class ContentPresenterImpl implements ContentPresenter {
    EventBus eventBus;
    ContentView loginView;
    ContentInteractor contentInteractor;

    public ContentPresenterImpl(ContentView loginView) {
        this.loginView = loginView;
        this.eventBus = EventBus.getInstance();

        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        this.contentInteractor = new ContentInteractorImpl(session);
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
        this.loginView = null;
    }

    @Override
    public void getImages() {
        this.contentInteractor.getImageItemsList();
    }

    @Override
    public void getHashtags() {
        this.contentInteractor.getHashtagItemsList();
    }

    @Override
    public void onEventMainThread(TweetEvent event) {
        String errorMsg = event.getError();
        if (errorMsg == null) {
            if (this.loginView != null) {
                List<TweetModel> items = event.getItems();
                if (items != null && !items.isEmpty()) {
                    this.loginView.setItems(items);
                }

            }
        } else {
            if (this.loginView != null) {
                this.loginView.onImagesError(errorMsg);
            }
        }

    }
}
