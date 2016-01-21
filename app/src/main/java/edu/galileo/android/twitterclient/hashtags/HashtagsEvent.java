package edu.galileo.android.twitterclient.hashtags;

import java.util.List;

import edu.galileo.android.twitterclient.entities.Hashtag;

/**
 * Created by ykro.
 */
public class HashtagsEvent {
    private String error;
    private List<Hashtag> hashtags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
