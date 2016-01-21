package edu.galileo.android.twitterclient.hashtags;


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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.adapters.HashtagsAdapter;
import edu.galileo.android.twitterclient.entities.Hashtag;

public class HashtagsFragment extends Fragment
                            implements HashtagsView, OnItemClickListener {
    private List<Hashtag> items;
    private RecyclerView.Adapter adapter;
    private HashtagsPresenter hashtagsPresenter;

    @Bind(R.id.container) FrameLayout container;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.recyclerView)  RecyclerView recyclerView;

    public HashtagsFragment() {
        hashtagsPresenter = new HashtagsPresenterImpl(this);
    }

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

        items = new ArrayList<Hashtag>();
        adapter = new HashtagsAdapter(getContext().getApplicationContext(), items, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        hashtagsPresenter.getHashtagTweets();
        return view;
    }

    @Override
    public void onImagesError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setHashtags(List<Hashtag> newItems) {
        items.addAll(newItems);
        adapter.notifyDataSetChanged();
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
