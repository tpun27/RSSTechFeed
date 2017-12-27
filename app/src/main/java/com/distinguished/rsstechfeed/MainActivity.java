package com.distinguished.rsstechfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    static final String BASE_URL = "https://news.google.com/";
    RecyclerView articleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateApp();
    }

    public void populateApp() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        GoogleNewsAPI googleNewsAPI = retrofit.create(GoogleNewsAPI.class);

        Call<RSSFeed> call = googleNewsAPI.loadRSSFeed();
        call.enqueue(new Callback<RSSFeed>() {
            @Override
            public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
                if (response.isSuccessful()) {
                    RSSFeed rss = response.body();

                    List<Article> articleList = rss.getArticleList();
                    cleanImageLinks(articleList);

                    articleRecyclerView = (RecyclerView) findViewById(R.id.articleRecyclerView);
                    articleRecyclerView.setHasFixedSize(true);
                    articleRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    ArticleAdapter adapter = new ArticleAdapter(rss.getArticleList());
                    articleRecyclerView.setAdapter(adapter);
                }
                else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RSSFeed> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void cleanImageLinks(List<Article> articleList) {
        for (Article article : articleList) {
            String imageLink = article.getImageLink();
            imageLink = imageLink.substring(imageLink.indexOf("http"));
            imageLink = imageLink.substring(0, imageLink.indexOf('"'));
            article.setImageLink(imageLink);
        }
    }
}
