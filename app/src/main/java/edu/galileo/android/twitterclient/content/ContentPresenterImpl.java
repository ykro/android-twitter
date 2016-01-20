package edu.galileo.android.twitterclient.content;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.List;

import edu.galileo.android.twitterclient.entities.TweetEntity;
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
    public void getTweets(int type){
        if (this.loginView != null){
            loginView.hideList();
            loginView.showProgress();
        }

        if (type == TweetEntity.IMAGES_CONTENT) {
            this.contentInteractor.getImageItemsList();
        } else {
            this.contentInteractor.getHashtagItemsList();
        }

    }

    @Override
    public void onEventMainThread(TweetEvent event) {
        String errorMsg = event.getError();
        if (this.loginView != null) {
            loginView.showList();
            loginView.hideProgress();
            if (errorMsg != null) {
                this.loginView.onImagesError(errorMsg);
            } else {
                List<TweetEntity> items = event.getItems();
                if (items != null && !items.isEmpty()) {
                    this.loginView.setItems(items);
                }
            }
        }
    }
}
