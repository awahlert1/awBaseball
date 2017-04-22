package edu.missouriwestern.awahlert1.csc445.awbaseball;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Austin on 3/26/2017.
 */

public class Player {

    private UUID mID;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public void setNumber(int number){
        mNumber = String.valueOf(number);
    }

    public boolean isPitcher() {
        return isPitcher;
    }

    public void setPitcher(boolean pitcher) {
        isPitcher = pitcher;
        setPositions();
    }

    public boolean isCatcher() {
        return isCatcher;
    }

    public void setCatcher(boolean catcher) {
        isCatcher = catcher;
        setPositions();
    }

    public boolean isInfield() {
        return isInfield;
    }

    public void setInfield(boolean infield) {
        isInfield = infield;
        setPositions();
    }

    public boolean isOutfield() {
        return isOutfield;
    }

    public void setOutfield(boolean outfield) {
        isOutfield = outfield;
        setPositions();
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions() {
        positions = "";
        if(isPitcher){
            positions += R.string.symbol_pitcher;
        }
        if (isCatcher){
            positions += R.string.symbol_catcher;
        }
        if (isInfield){
            positions += R.string.symbol_infield;
        }
        if (isOutfield){
            positions += R.string.symbol_outfield;
        }
    }


    public Date getLastUpdate() {
        return mLastUpdate;
    }

    public void setLastUpdate() {
        mLastUpdate = new Date();
    }

    private String mLastName;
    private String mFirstName;
    private String mNumber;  //players jersey number
    private Date mLastUpdate;
    private Date mDate;
    private boolean isPitcher;
    private boolean isCatcher;
    private boolean isInfield;
    private boolean isOutfield;
    private String positions; //this is a string representing the positions. generated be the setPositions method

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

    public void setID() {
        mID = UUID.randomUUID();
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }



    public Player() {
        //Generate unigue identifier
        setID();
        setLastUpdate();
        setLastName("");
        setFirstName("");
        setNumber(99);
        setPitcher(false);
        setCatcher(false);
        setInfield(false);
        setOutfield(true);
        setPositions();
        mDate = new Date();
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + mID +
                "mLastName='" + mLastName + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mNumber='" + mNumber + '\'' +
                ", mLastUpdate=" + mLastUpdate +
                ", positions='" + positions + '\'' +
                '}';
    }


}
