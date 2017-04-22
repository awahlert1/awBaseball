package edu.missouriwestern.awahlert1.csc445.awbaseball;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Austin on 3/26/2017.
 */

public class BaseballFragment extends Fragment {
    private Player mPlayer;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mPlayer = new Player();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_baseball, container, false);

        mTitleField = (EditText)v.findViewById(R.id.baseball_lastName);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                mPlayer.setLastName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //this one too
            }
        });

        mDateButton = (Button)v.findViewById(R.id.baseball_date);
        mDateButton.setText(mPlayer.getDate().toString());
        mDateButton.setEnabled(false);

//        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.baseball_solved);
//        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                //set the baseball solved property
//                mPlayer.setSolved(isChecked);
//            }
//        });

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.player_ispitcher);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set the player pitcher property
                mPlayer.setPitcher(isChecked);
                if (!isChecked){
                    Log.d(TAG, "pitched box unchecked");
                }else{
                    Log.d(TAG, "pitcher box checked");
                }
            }
        });

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.player_iscatchfield);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set the player catcher property
                mPlayer.setCatcher(isChecked);
                if (!isChecked){
                    Log.d(TAG, "catcher box unchecked");
                }else{
                    Log.d(TAG, "catcher box checked");
                }

            }
        });

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.player_isinfield);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set the player infield property
                mPlayer.setInfield(isChecked);
                if (!isChecked){
                    Log.d(TAG, "infield box unchecked");
                }else{
                    Log.d(TAG, "infield box checked");
                }
            }
        });

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.player_isoutfield);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set the player outfield property
                mPlayer.setOutfield(isChecked);
                if (!isChecked){
                    Log.d(TAG, "outfield box unchecked");
                }else{
                    Log.d(TAG, "outfield box checked");
                }
            }
        });


        return v;
    }
}
