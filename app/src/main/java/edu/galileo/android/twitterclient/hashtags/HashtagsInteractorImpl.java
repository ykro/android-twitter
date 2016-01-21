package edu.galileo.android.twitterclient.hashtags;

import edu.galileo.android.twitterclient.api.HashtagsAPI;

/**
 * Created by ykro.
 */
public class HashtagsInteractorImpl implements HashtagsInteractor {
    private HashtagsAPI hashtagsAPI;

    public HashtagsInteractorImpl() {
        this.hashtagsAPI = HashtagsAPI.getInstance();
    }

    @Override
    public void getHashtagItemsList() {
        hashtagsAPI.getHashtags();
    }
}
