package edu.galileo.android.twitterclient.entities;

import java.util.List;

/**
 * Created by ykro.
 */
public class Hashtag {
    private String id;
    List<String> hashtags;
    private String imageURL;
    private String tweetText;
    private int favoriteCount;
    private final static String BASE_TWEET_URL = "https://twitter.com/statuses/";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTweetURL() {
        return BASE_TWEET_URL + this.id;
    }
}
