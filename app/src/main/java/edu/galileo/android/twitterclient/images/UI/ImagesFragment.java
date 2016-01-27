package edu.galileo.android.twitterclient.images.ui;


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

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.images.entities.Image;
import edu.galileo.android.twitterclient.images.di.DaggerImagesComponent;
import edu.galileo.android.twitterclient.images.di.ImagesModule;
import edu.galileo.android.twitterclient.images.ImagesPresenter;
import edu.galileo.android.twitterclient.images.adapters.ImagesAdapter;
import edu.galileo.android.twitterclient.lib.di.LibsModule;

public class ImagesFragment extends Fragment
                            implements ImagesView, OnItemClickListener {

    @Inject
    ImagesAdapter adapter;
    @Inject
    ImagesPresenter imagesPresenter;

    @Bind(R.id.container) FrameLayout container;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.recyclerView)  RecyclerView recyclerView;

    @Override
    public void onResume() {
        super.onResume();
        imagesPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        imagesPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        imagesPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        setupInjection();
        setupRecyclerView();
        imagesPresenter.getImageTweets();

        return view;
    }

    private void setupInjection() {
        DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(this))
                .imagesModule(new ImagesModule(this, this))
                .build()
                .inject(this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onImagesError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setImages(List<Image> items) {
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
    public void onItemClick(Image tweet) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweet.getTweetURL()));
        startActivity(intent);
    }
}
