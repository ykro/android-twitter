package edu.galileo.android.twitterclient.events;

import java.util.List;

import edu.galileo.android.twitterclient.entities.TweetEntity;

/**
 * Created by ykro.
 */
public class TweetEvent {
    private String error;
    private List<TweetEntity> items;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<TweetEntity> getItems() {
        return items;
    }

    public void setItems(List<TweetEntity> items) {
        this.items = items;
    }
}
