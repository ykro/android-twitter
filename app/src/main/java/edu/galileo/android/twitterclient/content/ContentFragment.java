package edu.galileo.android.twitterclient.content;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import edu.galileo.android.twitterclient.adapters.ImagesAdapter;
import edu.galileo.android.twitterclient.entities.TweetModel;

public class ContentFragment extends Fragment
                            implements ContentView {
    public final static int IMAGES_CONTENT = 0;
    public final static int HASHTAGS_CONTENT = 1;
    public final static String CONTENT_TYPE_PARAM = "content_type";

    private int contentType;
    private List<TweetModel> items;
    private RecyclerView.Adapter adapter;
    private ContentPresenter contentPresenter;
    private RecyclerView.LayoutManager layoutManager;

    @Bind(R.id.container) FrameLayout container;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.recyclerView)  RecyclerView recyclerView;

    public ContentFragment() {
        contentPresenter = new ContentPresenterImpl(this);
    }

    public static ContentFragment newImagesFragmentInstance() {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt(CONTENT_TYPE_PARAM, IMAGES_CONTENT);
        fragment.setArguments(args);
        return fragment;
    }

    public static ContentFragment newHashtagsFragmentInstance() {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt(CONTENT_TYPE_PARAM, HASHTAGS_CONTENT);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new ArrayList<TweetModel>();
        if (getArguments() != null) {
            this.contentType = getArguments().getInt(CONTENT_TYPE_PARAM);

            if (contentType == IMAGES_CONTENT) {
                layoutManager = new GridLayoutManager(getActivity(), 2);
                adapter = new ImagesAdapter(getContext().getApplicationContext(), items);
                contentPresenter.getImages();

            } else if (contentType == HASHTAGS_CONTENT) {
                layoutManager = new LinearLayoutManager(getActivity());
                adapter = new HashtagsAdapter(items);
                contentPresenter.getHashtags();
            }
        }
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

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onImagesError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setItems(List<TweetModel> newItems) {
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

}
