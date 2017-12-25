package com.distinguished.rsstechfeed;

public class GoogleNewsAPIUtils {
    public static GoogleNewsAPI getGoogleNewsAPI() {

        return RetrofitClient.getClient().create(GoogleNewsAPI.class);
    }
}
