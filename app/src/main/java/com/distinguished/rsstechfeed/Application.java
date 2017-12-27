package com.distinguished.rsstechfeed;

import android.text.Html;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Application {

    static final String BASE_URL = "https://news.google.com/";

    public static void main(String[] args ) {
        populateApp();
    }

    public static void populateApp() {
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
                    for (Article article : articleList) {
                        String imageLink = article.getImageLink();
                        imageLink = imageLink.substring(imageLink.indexOf("http"));
                        System.out.println(imageLink.substring(0, imageLink.indexOf('"')));
                        //cleanImageLink(imageLink);
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
        });
    }

    public static void cleanImageLink(String imageLink) {
        String cleanImageString;
        cleanImageString = imageLink.substring(imageLink.indexOf("src"));
        cleanImageString = cleanImageString.substring(0, cleanImageString.indexOf(" "));

        System.out.println(cleanImageString);
        //Log.d("ImageLink:", cleanImageString);
    }
}
