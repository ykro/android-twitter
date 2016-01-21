package edu.galileo.android.twitterclient.api;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by ykro.
 */
public class APIHelper {
    CustomTwitterApiClient apiClient;
    private static class SingletonHolder {
        private static final APIHelper INSTANCE = new APIHelper();
    }

    public static APIHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public APIHelper(){
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        apiClient = new CustomTwitterApiClient(session);
    }

    public CustomTwitterApiClient getAPIClient() {
        return apiClient;
    }
}
