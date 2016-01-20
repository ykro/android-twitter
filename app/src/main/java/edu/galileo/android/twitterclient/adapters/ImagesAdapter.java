package edu.galileo.android.twitterclient.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.entities.TweetEntity;
import edu.galileo.android.twitterclient.lib.ImageLoading;

/**
 * Created by ykro.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private List<TweetEntity> items;
    private ImageLoading imageLoadingHelper;

    public ImagesAdapter(Context context, List<TweetEntity> items) {
        this.items = items;
        imageLoadingHelper = new ImageLoading(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TweetEntity tweet = items.get(position);
        holder.txtTweet.setText(tweet.getTweetText());
        //imageLoadingHelper.loadImage(tweet.getImageURL(), holder.imgMedia);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtTweet) TextView txtTweet;
        @Bind(R.id.imgMedia) ImageView imgMedia;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
