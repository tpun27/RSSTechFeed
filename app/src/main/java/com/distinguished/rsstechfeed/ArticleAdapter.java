package com.distinguished.rsstechfeed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article> articleList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView articleImageView;
        public TextView articleTitleTextView, articleDateTextView;

        public ViewHolder(View v) {
            super(v);
            articleImageView = (ImageView) v.findViewById(R.id.articleImageView);
            articleTitleTextView = (TextView) v.findViewById(R.id.articleTitleTextView);
            articleDateTextView = (TextView) v.findViewById(R.id.articleDateTextView);
        }
    }

    public ArticleAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder holder, int position) {
        final Article article = ((Article) articleList.get(position));
        Picasso.with(holder.articleImageView.getContext()).load(article.getImageLink()).resize(150, 150).into(holder.articleImageView);
        holder.articleDateTextView.setText(article.getPubDate());
        holder.articleTitleTextView.setText(article.getTitle());
        holder.articleTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getArticleLink()));
                context.startActivity(browserIntent);
            }
        });
        holder.articleDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getArticleLink()));
                context.startActivity(browserIntent);
            }
        });
        holder.articleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getArticleLink()));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
