
package com.example.android.footballnews;

/**
 * An {@link FootballNew} object contains information related to a single football new
 */
public class FootballNew {

    /** Title of the football new */
    private String mTitle;

    /** Date of publication */
    private String mDate;

    /** Author of publication */
    private String mAuthor;

    /** Section of the new */
    private String mSection;

    /** Website URL of the new */
    private String mUrl;

    /**
     * Constructs a new {@link FootballNew} object.
     *
     * @param Title is the heading of the football new
     * @param Date is the time where the new was published
     * @param author is the person who wrote the new
     * @param section is the type of the new
     * @param url is the website URL to find the contents of the new     *
     */
    public FootballNew(String Title, String Date, String author, String section, String url) {
        mTitle = Title;
        mDate = Date;
        mAuthor = author;
        mSection = section;
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
     * Returns the author of the new.
     */
    public String getAuthor() {
        return mAuthor;
    }

    /**
     * Returns the section of new.
     */
    public String getSection() {
        return mSection;
    }

    /**
     * Returns the website URL to to find the contents of the new.
     */
    public String getUrl() {
        return mUrl;
    }
}
