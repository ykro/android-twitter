package edu.galileo.android.twitterclient.images;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import edu.galileo.android.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.lib.EventBus;

/**
 * Created by ykro.
 */
public class ImagesRepositoryImpl implements ImagesRepository {
    private final CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    @Inject
    public ImagesRepositoryImpl(CustomTwitterApiClient client) {
        this.client = client;
    }

    public void getImages() {

        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<Image> items = new ArrayList<Image>();
                        for (Tweet tweet : result.data) {
                            if (checkIfTweetHasImage(tweet)) {
                                Image tweetModel = new Image();

                                tweetModel.setId(tweet.idStr);
                                tweetModel.setFavoriteCount(tweet.favoriteCount);

                                String tweetText = tweet.text;
                                tweetText = tweetText.substring(0,tweetText.indexOf("http"));
                                tweetModel.setTweetText(tweetText);

                                MediaEntity currentPhoto = tweet.entities.media.get(0);
                                String imageURL = currentPhoto.mediaUrl;
                                tweetModel.setImageURL(imageURL);

                                items.add(tweetModel);
                            }
                        }
                        Collections.sort(items, new Comparator<Image>() {
                            public int compare(Image t1, Image t2) {
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

    private boolean checkIfTweetHasImage(Tweet tweet) {
        return  tweet.entities != null &&
                tweet.entities.media != null &&
                !tweet.entities.media.isEmpty();
    }

    private void postEvent(String error) {
        ImagesEvent event = new ImagesEvent();
        event.setError(error);
        EventBus.getInstance().post(event);
    }

    private void postEvent(List<Image> items) {
        ImagesEvent event = new ImagesEvent();
        event.setImages(items);
        EventBus.getInstance().post(event);
    }
}
