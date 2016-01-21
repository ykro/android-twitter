package edu.galileo.android.twitterclient.content;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import edu.galileo.android.twitterclient.adapters.ImagesAdapter;
import edu.galileo.android.twitterclient.entities.TweetEntity;

public class ImagesFragment extends Fragment
                            implements ContentView, OnItemClickListener {
    private List<TweetEntity> items;
    private RecyclerView.Adapter adapter;
    private ContentPresenter contentPresenter;

    @Bind(R.id.container) FrameLayout container;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.recyclerView)  RecyclerView recyclerView;

    public ImagesFragment() {
        contentPresenter = new ContentPresenterImpl(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        contentPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        contentPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        contentPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        items = new ArrayList<TweetEntity>();
        adapter = new ImagesAdapter(this, items, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);

        contentPresenter.getTweets(TweetEntity.IMAGES_CONTENT);
        return view;
    }

    @Override
    public void onImagesError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setItems(List<TweetEntity> newItems) {
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
    public void onItemClick(TweetEntity tweet) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweet.getTweetURL()));
        startActivity(intent);
    }
}
