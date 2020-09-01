
package com.example.android.footballnews;

/**
 * An {@link FootballNew} object contains information related to a single earthquake.
 */
public class FootballNew {

    /** Title of the football new */
    private String mTitle;

    /** Date of publication */
    private String mDate;

    /** Website URL of the new */
    private String mUrl;

    /**
     * Constructs a new {@link FootballNew} object.
     *
     * @param Title is the heading of the football new
     * @param Date is the time where the new was published
     * @param url is the website URL to find the contents of the new
     */
    public FootballNew(String Title, String Date, String url) {
        mTitle = Title;
        mDate = Date;
        mUrl = url;
    }

    /**
     * Returns the title of the football new.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Returns the date of publication.
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Returns the website URL to to find the contents of the new.
     */
    public String getUrl() {
        return mUrl;
    }
}
