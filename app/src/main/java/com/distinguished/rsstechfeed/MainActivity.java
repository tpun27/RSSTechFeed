package com.distinguished.rsstechfeed;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    static final String BASE_URL = "https://news.google.com/";
    TextView title1, title2, title3;
    TextView link1, link2, link3;
    Button linkButton;

    RecyclerView articleRecyclerView;
    List<Article> mainArticleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateFields();
//        title1 = (TextView) findViewById(R.id.title1);
//        link1 = (TextView) findViewById(R.id.link1);
//
//        title2 = (TextView) findViewById(R.id.title2);
//        link2 = (TextView) findViewById(R.id.link2);
//
//        title3 = (TextView) findViewById(R.id.title3);
//        link3 = (TextView) findViewById(R.id.link3);


//        articleRecyclerView = (RecyclerView) findViewById(R.id.articleRecyclerView);
//        articleRecyclerView.setHasFixedSize(true);
//        articleRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//
//        ArticleAdapter adapter = new ArticleAdapter(mainArticleList);
//        articleRecyclerView.setAdapter(adapter);


//        Button linkButton = (Button) findViewById(R.id.linkButton);
//        linkButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link1.getText().toString()));
//                startActivity(browserIntent);
//            }
//        });

    }

    public void populateFields() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        GoogleNewsAPI googleNewsAPI = retrofit.create(GoogleNewsAPI.class);

        Call<RSSFeed> call = googleNewsAPI.loadRSSFeed();
        call.enqueue(new Callback<RSSFeed>() {
            @Override
            public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
                if (response.isSuccessful()) {
                    RSSFeed rss = response.body();
                    mainArticleList = rss.getArticleList();

                    articleRecyclerView = (RecyclerView) findViewById(R.id.articleRecyclerView);
                    articleRecyclerView.setHasFixedSize(true);
                    articleRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    ArticleAdapter adapter = new ArticleAdapter(mainArticleList);
                    articleRecyclerView.setAdapter(adapter);
//                    Article article;
//                    article = articleList.get(0);
//                    title1.setText(article.getTitle());
//                    link1.setText(article.getLink());
//                    Log.d("List Length", Integer.toString(articleList.size()));
//                    Log.d("Title1", article.getTitle());
//                    Log.d("Link1", article.getLink());
//
//                    article = articleList.get(1);
//                    title2.setText(articleList.get(1).getTitle());
//                    link2.setText(article.getLink());
//                    Log.d("Title2", article.getTitle());
//                    Log.d("Link2", article.getLink());
//
//                    article = articleList.get(2);
//                    title3.setText(articleList.get(2).getTitle());
//                    link3.setText(article.getLink());
//                    Log.d("Title3", article.getTitle());
//                    Log.d("Link3", article.getLink());
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
}
