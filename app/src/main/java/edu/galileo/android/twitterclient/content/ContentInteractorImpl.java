package edu.galileo.android.twitterclient.content;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.galileo.android.twitterclient.api.ApiClient;
import edu.galileo.android.twitterclient.entities.TweetEntity;
import edu.galileo.android.twitterclient.events.TweetEvent;
import edu.galileo.android.twitterclient.lib.EventBus;

/**
 * Created by ykro.
 */
public class ContentInteractorImpl implements ContentInteractor {
    ApiClient client;
    private final static int TWEET_COUNT = 50;

    public ContentInteractorImpl(TwitterSession session) {
        this.client = new ApiClient(session);
    }

    @Override
    public void getImageItemsList() {
        final TweetEvent event = new TweetEvent();
        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<TweetEntity> items = new ArrayList<TweetEntity>();
                        for (Tweet tweet : result.data) {

                            if (checkIfTweetHasImage(tweet)) {
                                TweetEntity tweetModel = new TweetEntity();

                                String tweetText = tweet.text;
                                tweetText = tweetText.substring(0,tweetText.indexOf("http"));
                                tweetModel.setTweetText(tweetText);

                                int favCount = tweet.favoriteCount;
                                tweetModel.setFavoriteCount(favCount);

                                String tweetId = tweet.idStr;
                                tweetModel.setId(tweetId);

                                MediaEntity currentPhoto = tweet.entities.media.get(0);
                                String imageURL = currentPhoto.mediaUrl;
                                tweetModel.setImageURL(imageURL);

                                items.add(tweetModel);
                            }
                        }
                        Collections.sort(items, new Comparator<TweetEntity>() {
                            public int compare(TweetEntity t1, TweetEntity t2) {
                                return t2.getFavoriteCount() - t1.getFavoriteCount();
                            }
                        });

                        postEvent(items, TweetEntity.IMAGES_CONTENT);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        postEvent(e.getMessage(), TweetEntity.IMAGES_CONTENT);
                    }
                }
        );
    }

    @Override
    public void getHashtagItemsList() {
        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<TweetEntity> items = new ArrayList<TweetEntity>();
                        for (Tweet tweet : result.data) {

                            if (checkIfTweetHasHashtags(tweet)) {
                                TweetEntity tweetModel = new TweetEntity();

                                tweetModel.setTweetText(tweet.text);

                                int favCount = tweet.favoriteCount;
                                tweetModel.setFavoriteCount(favCount);

                                String tweetId = tweet.idStr;
                                tweetModel.setId(tweetId);

                                List<String> hashtags = new ArrayList<String>();
                                for (HashtagEntity hashtag : tweet.entities.hashtags) {
                                    hashtags.add(hashtag.text);
                                }
                                tweetModel.setHashtags(hashtags);
                                
                                items.add(tweetModel);
                            }
                        }
                        Collections.sort(items, new Comparator<TweetEntity>() {
                            public int compare(TweetEntity t1, TweetEntity t2) {
                                return t2.getFavoriteCount() - t1.getFavoriteCount();
                            }
                        });
                        postEvent(items, TweetEntity.HASHTAGS_CONTENT);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        postEvent(e.getMessage(), TweetEntity.HASHTAGS_CONTENT);
                    }
                }
        );
    }

    private boolean checkIfTweetHasImage(Tweet tweet) {
        return  tweet.entities != null &&
                tweet.entities.media != null &&
                !tweet.entities.media.isEmpty();
    }

    private boolean checkIfTweetHasHashtags(Tweet tweet) {
        return  tweet.entities != null &&
                tweet.entities.hashtags != null &&
                !tweet.entities.hashtags.isEmpty();
    }

    private void postEvent(String error, int type) {
        TweetEvent event = new TweetEvent();
        event.setContentType(type);
        event.setError(error);
        EventBus.getInstance().post(event);
    }

    private void postEvent(List<TweetEntity> items, int type) {
        TweetEvent event = new TweetEvent();
        event.setContentType(type);
        event.setItems(items);
        EventBus.getInstance().post(event);
    }
}
