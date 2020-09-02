package com.example.android.footballnews;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FootballNewActivity extends AppCompatActivity
        implements LoaderCallbacks<List<FootballNew>> {

    private static final String LOG_TAG = FootballNewActivity.class.getName();

    /** URL for football news data from the The Guardian dataset */
    private static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com";

    /**
     * Constant value for the football new loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int FOOTBALLNEW_LOADER_ID = 1;

    /** Adapter for the list of football news */
    private FootballNewAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.football_new_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView footballNewListView = findViewById(R.id.list);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        footballNewListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of football news as input
        mAdapter = new FootballNewAdapter(this, new ArrayList<FootballNew>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        footballNewListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with the contents of the selected football new.
        footballNewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current football new that was clicked on
                FootballNew currentFootballNew = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri footballNewUri = Uri.parse(currentFootballNew.getUrl());

                // Create a new intent to view the football new URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, footballNewUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(FOOTBALLNEW_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<FootballNew>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendPath("football");
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("api-key", "test");

        return new FootballNewLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<FootballNew>> loader, List<FootballNew> footballNews) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No football news found."
        mEmptyStateTextView.setText(R.string.no_footballNews);

        // Clear the adapter of previous football news data
        //mAdapter.clear();

        // If there is a valid list of {@link FootballNew}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (footballNews != null && !footballNews.isEmpty()) {
            mAdapter.addAll(footballNews);
//            updateUi(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<FootballNew>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}

