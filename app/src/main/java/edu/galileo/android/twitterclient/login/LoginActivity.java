package edu.galileo.android.twitterclient.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.galileo.android.twitterclient.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "Qco9nsIJQQ39jL44lkjuQzxzM";
    private static final String TWITTER_SECRET = "7k8fiQXZCxeuTcy3jYamwIDfmrcbkw8fFFdewoL7bgigXMiTYa";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_login);
    }
}
