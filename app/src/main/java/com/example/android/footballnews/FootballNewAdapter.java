package com.example.android.footballnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * An {@link FootballNewAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link FootballNew} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class FootballNewAdapter extends ArrayAdapter<FootballNew> {

    /**
     * The part of the date string that separates the date and the hour
     */
    private static final String DATE_SEPARATOR = "T";

    /**
     * Constructs a new {@link FootballNewAdapter}.
     *
     * @param context      of the app
     * @param footballNews is the list of football news, which is the data source of the adapter
     */
    public FootballNewAdapter(Context context, List<FootballNew> footballNews) {
        super(context, 0, footballNews);
    }

    /**
     * Returns a list item view that displays information about the football news at the given position
     * in the list of football news.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.football_new_list_item, parent, false);
        }

        // Find the football new at the given position in the list of football news
        FootballNew currentFootballNew = getItem(position);

        // Find the TextView with view ID title
        TextView titleView = listItemView.findViewById(R.id.title);
        // Get the title of the current football new
        String currentTitle = currentFootballNew.getTitle();
        // Display the title of the current football new in that TextView
        titleView.setText(currentTitle);

        // Get the original date string from the FootballNew object,
        // which can be in the format of "2020-08-31T23:01:21Z" or "2019-07-17T08:34:17Z".
        String originalDate = currentFootballNew.getDate();

        // Separate the original date (i.e. "2020-08-31T23:01:21Z") into date (2020-08-31) and
        // time (23:01) changing the format from hh:mm:ssZ to hh:mm
        // Split the string into different parts (as an array of Strings)
        String[] parts = originalDate.split(DATE_SEPARATOR);
        String date = parts[0];
        // Change the format of the time
        String time = parts[1].substring(0, 5);

        // Find the TextView with view ID date
        TextView DateView = listItemView.findViewById(R.id.date);
        // Display the date of the current football new in that TextView
        DateView.setText(date);

        // Find the TextView with view ID time
        TextView timeView = listItemView.findViewById(R.id.time);
        // Display the time offset of the current football new in that TextView
        timeView.setText(time);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}