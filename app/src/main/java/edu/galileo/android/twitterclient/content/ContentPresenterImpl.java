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
    private int contentType;
    private EventBus eventBus;
    private ContentView contentView;
    private ContentInteractor contentInteractor;

    public ContentPresenterImpl(ContentView contentView, int contentType) {
        this.contentType = contentType;
        this.contentView = contentView;
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
        this.contentView = null;
    }

    @Override
    public void getTweets(){
        if (this.contentView != null){
            contentView.hideList();
            contentView.showProgress();
        }

        if (contentType == TweetEntity.IMAGES_CONTENT) {
            this.contentInteractor.getImageItemsList();
        } else {
            this.contentInteractor.getHashtagItemsList();
        }
    }

    @Override
    public void onEventMainThread(TweetEvent event) {
        String errorMsg = event.getError();
        if (this.contentView != null) {
            contentView.showList();
            contentView.hideProgress();
            if (errorMsg != null) {
                this.contentView.onImagesError(errorMsg);
            } else {
                List<TweetEntity> items = event.getItems();
                if (items != null
                        && !items.isEmpty()
                        && event.getContentType() == contentType) {
                    this.contentView.setItems(items);
                }
            }
        }
    }
}
