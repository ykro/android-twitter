package edu.galileo.android.twitterclient.images;


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
import edu.galileo.android.twitterclient.entities.Image;

public class ImagesFragment extends Fragment
                            implements ImagesView, OnItemClickListener {
    private List<Image> items;
    private RecyclerView.Adapter adapter;
    private ImagesPresenter imagesPresenter;

    @Bind(R.id.container) FrameLayout container;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.recyclerView)  RecyclerView recyclerView;

    public ImagesFragment() {
        imagesPresenter = new ImagesPresenterImpl(this);
    }

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

        items = new ArrayList<Image>();
        adapter = new ImagesAdapter(this, items, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);

        imagesPresenter.getImageTweets();
        return view;
    }

    @Override
    public void onImagesError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setImages(List<Image> newItems) {
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
    public void onItemClick(Image tweet) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweet.getTweetURL()));
        startActivity(intent);
    }
}
