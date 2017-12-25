package com.distinguished.rsstechfeed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GoogleNewsAPI {

    @GET("news/rss/headlines/section/topic/TECHNOLOGY")
    Call<RSSFeed> loadRSSFeed();
}
