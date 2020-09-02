package com.example.android.footballnews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Loads a list of football news by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class FootballNewLoader extends AsyncTaskLoader<List<FootballNew>> {

    /** Tag for log messages */
    private static final String LOG_TAG = FootballNewLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link FootballNewLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public FootballNewLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<FootballNew> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of football news.
        List<FootballNew> footballNews = QueryUtils.fetchFootballNewsData(mUrl);
        return footballNews;
    }
}
