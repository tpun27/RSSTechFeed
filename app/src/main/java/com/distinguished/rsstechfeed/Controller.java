package com.distinguished.rsstechfeed;

import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Controller implements Callback<RSSFeed> {

    static final String BASE_URL = "https://news.google.com/";

    public void start() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        GoogleNewsAPI googleNewsAPI = retrofit.create(GoogleNewsAPI.class);

        Call<RSSFeed> call = googleNewsAPI.loadRSSFeed();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
        if (response.isSuccessful()) {
            RSSFeed rss = response.body();
            List<Article> articleList = rss.getArticleList();

            for (Article article : articleList) {
                //System.out.println(article.getTitle());
                //System.out.println(article.getLink());
            }

        }
        else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<RSSFeed> call, Throwable t) {
        t.printStackTrace();
    }
}
