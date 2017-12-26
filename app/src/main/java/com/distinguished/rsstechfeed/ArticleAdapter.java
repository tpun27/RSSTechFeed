package com.distinguished.rsstechfeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article> articleList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView articleTextView;

        public ViewHolder(View v) {
            super(v);
            articleTextView = (TextView) v.findViewById(R.id.articleTextView);
        }
    }

    public ArticleAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder holder, int position) {
        Article article = ((Article) articleList.get(position));
        holder.articleTextView.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
