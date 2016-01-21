package edu.galileo.android.twitterclient.hashtags;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.galileo.android.twitterclient.api.ApiClient;
import edu.galileo.android.twitterclient.entities.Hashtag;
import edu.galileo.android.twitterclient.events.HashtagEvent;
import edu.galileo.android.twitterclient.lib.EventBus;

/**
 * Created by ykro.
 */
public class HashtagsInteractorImpl implements HashtagsInteractor {
    ApiClient client;
    private final static int TWEET_COUNT = 50;

    public HashtagsInteractorImpl(TwitterSession session) {
        this.client = new ApiClient(session);
    }

    @Override
    public void getHashtagItemsList() {
        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<Hashtag> items = new ArrayList<Hashtag>();
                        for (Tweet tweet : result.data) {

                            if (checkIfTweetHasHashtags(tweet)) {
                                Hashtag tweetModel = new Hashtag();

                                tweetModel.setId(tweet.idStr);
                                tweetModel.setTweetText(tweet.text);
                                tweetModel.setAuthor(tweet.user.screenName);
                                tweetModel.setFavoriteCount(tweet.favoriteCount);

                                List<String> hashtags = new ArrayList<String>();
                                for (HashtagEntity hashtag : tweet.entities.hashtags) {
                                    hashtags.add(hashtag.text);
                                }
                                tweetModel.setHashtags(hashtags);
                                
                                items.add(tweetModel);
                            }
                        }
                        Collections.sort(items, new Comparator<Hashtag>() {
                            public int compare(Hashtag t1, Hashtag t2) {
                                return t2.getFavoriteCount() - t1.getFavoriteCount();
                            }
                        });
                        postEvent(items);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        postEvent(e.getMessage());
                    }
                }
        );
    }

    private boolean checkIfTweetHasHashtags(Tweet tweet) {
        return  tweet.entities != null &&
                tweet.entities.hashtags != null &&
                !tweet.entities.hashtags.isEmpty();
    }

    private void postEvent(String error) {
        HashtagEvent event = new HashtagEvent();
        event.setError(error);
        EventBus.getInstance().post(event);
    }

    private void postEvent(List<Hashtag> items) {
        HashtagEvent event = new HashtagEvent();
        event.setHashtags(items);
        EventBus.getInstance().post(event);
    }
}
