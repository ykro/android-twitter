package edu.galileo.android.twitterclient.hashtags.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.TwitterAppModule;
import edu.galileo.android.twitterclient.entities.Hashtag;
import edu.galileo.android.twitterclient.hashtags.HashtagsPresenter;
import edu.galileo.android.twitterclient.hashtags.adapters.HashtagsAdapter;
import edu.galileo.android.twitterclient.hashtags.di.DaggerHashtagsComponent;
import edu.galileo.android.twitterclient.hashtags.di.HashtagsModule;
import edu.galileo.android.twitterclient.lib.LibsModule;

public class HashtagsFragment extends Fragment
                            implements HashtagsView, OnItemClickListener {

    @Inject
    HashtagsAdapter adapter;
    @Inject
    HashtagsPresenter hashtagsPresenter;

    @Bind(R.id.container) FrameLayout container;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.recyclerView)  RecyclerView recyclerView;

    @Override
    public void onResume() {
        super.onResume();
        hashtagsPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        hashtagsPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        hashtagsPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        setupInjection();
        setupRecyclerView();

        hashtagsPresenter.getHashtagTweets();
        return view;
    }

    private void setupInjection() {
        DaggerHashtagsComponent
                .builder()
                .libsModule(new LibsModule(this))
                .twitterAppModule(new TwitterAppModule(getContext()))
                .hashtagsModule(new HashtagsModule(this, this))
                .build()
                .inject(this);
    }


    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onImagesError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setHashtags(List<Hashtag> items) {
        adapter.setItems(items);
    }

    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(Hashtag tweet) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweet.getTweetURL()));
        startActivity(intent);
    }
}
