package edu.galileo.android.twitterclient.content;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import edu.galileo.android.twitterclient.api.ApiClient;
import edu.galileo.android.twitterclient.entities.TweetModel;
import edu.galileo.android.twitterclient.events.TweetEvent;
import edu.galileo.android.twitterclient.lib.EventBus;

/**
 * Created by ykro.
 */
public class ContentInteractorImpl implements ContentInteractor {
    ApiClient client;
    EventBus eventBus;

    private final static int TWEET_COUNT = 50;

    public ContentInteractorImpl(TwitterSession session) {
        this.client = new ApiClient(session);
        this.eventBus = EventBus.getInstance();
    }

    @Override
    public void getImageItemsList() {
        final TweetEvent event = new TweetEvent();
        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<TweetModel> items = new ArrayList<TweetModel>();
                        for (Tweet tweet : result.data) {

                            if (checkIfTweetHasImage(tweet)) {
                                TweetModel tweetModel = new TweetModel();

                                String tweetText = tweet.text;
                                tweetText = tweetText.substring(0,tweetText.indexOf("http"));
                                tweetModel.setTweetText(tweetText);

                                int favCount = tweet.favoriteCount;
                                tweetModel.setFavoriteCount(favCount);

                                String tweetId = tweet.idStr;
                                tweetModel.setId(tweetId);

                                //defaults to medium
                                MediaEntity currentPhoto = tweet.entities.media.get(0);
                                String imageURL = currentPhoto.mediaUrl;
                                if (currentPhoto.sizes.large != null) {
                                    imageURL += ":large";
                                }
                                tweetModel.setImageURL(imageURL);
                                items.add(tweetModel);
                            }
                        }

                        event.setItems(items);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        event.setError(e.getMessage());
                    }
                }
        );
        eventBus.post(event);
    }

    @Override
    public void getHashtagItemsList() {
        final TweetEvent event = new TweetEvent();
        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<TweetModel> items = new ArrayList<TweetModel>();
                        for (Tweet tweet : result.data) {

                            if (checkIfTweetHasImage(tweet)) {
                                TweetModel tweetModel = new TweetModel();

                                String tweetText = tweet.text;
                                tweetText = tweetText.substring(0,tweetText.indexOf("http"));
                                tweetModel.setTweetText(tweetText);

                                int favCount = tweet.favoriteCount;
                                tweetModel.setFavoriteCount(favCount);

                                String tweetId = tweet.idStr;
                                tweetModel.setId(tweetId);

                                //defaults to medium
                                MediaEntity currentPhoto = tweet.entities.media.get(0);
                                String imageURL = currentPhoto.mediaUrl;
                                if (currentPhoto.sizes.large != null) {
                                    imageURL += ":large";
                                }
                                tweetModel.setImageURL(imageURL);
                                items.add(tweetModel);
                            }
                        }

                        event.setItems(items);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        event.setError(e.getMessage());
                    }
                }
        );
        eventBus.post(event);
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
}
