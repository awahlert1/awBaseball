package edu.missouriwestern.awahlert1.csc445.awbaseball;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Austin on 3/26/2017.
 */

public class Baseball {

    private UUID mID;
    private String mTitle;
    private Date mDate;

    public Date getDate() {
        return mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public void setDate(Date date) {

        mDate = date;
    }

    private boolean mSolved;

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Baseball() {
        //Generate unigue identifier
        mID = UUID.randomUUID();
    }
}
