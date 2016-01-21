package edu.galileo.android.twitterclient.repositories;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import edu.galileo.android.twitterclient.api.CustomTwitterApiClient;

/**
 * Created by ykro.
 */
public class RepositoryHelper {
    CustomTwitterApiClient apiClient;
    private static class SingletonHolder {
        private static final RepositoryHelper INSTANCE = new RepositoryHelper();
    }

    public static RepositoryHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public RepositoryHelper(){
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        apiClient = new CustomTwitterApiClient(session);
    }

    public CustomTwitterApiClient getAPIClient() {
        return apiClient;
    }
}
