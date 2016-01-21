package edu.galileo.android.twitterclient.hashtags;

/**
 * Created by ykro.
 */
public class HashtagsInteractorImpl implements HashtagsInteractor {
    private HashtagsRepository hashtagsRepository;

    public HashtagsInteractorImpl() {
        this.hashtagsRepository = HashtagsRepository.getInstance();
    }

    @Override
    public void getHashtagItemsList() {
        hashtagsRepository.getHashtags();
    }
}
