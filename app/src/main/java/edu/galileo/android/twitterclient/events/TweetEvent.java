package edu.galileo.android.twitterclient.events;

import java.util.List;

import edu.galileo.android.twitterclient.entities.TweetModel;

/**
 * Created by ykro.
 */
public class TweetEvent {
    private String error;
    private List<TweetModel> items;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<TweetModel> getItems() {
        return items;
    }

    public void setItems(List<TweetModel> items) {
        this.items = items;
    }
}
