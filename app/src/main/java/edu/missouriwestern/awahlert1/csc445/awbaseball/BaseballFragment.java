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

import java.util.UUID;

import static android.content.ContentValues.TAG;

/**
 * Created by Austin on 3/26/2017.
 */

public class BaseballFragment extends Fragment {

    private static final String ARG_PLAYER_ID = "player_id";

    private Player mPlayer;
    private EditText mFirstName;
    private EditText mLastName;
    private Button mDateButton;
    private CheckBox mPitcherCheckBox;
    private CheckBox mCatcherCheckBox;
    private CheckBox mInfieldCheckBox;
    private CheckBox mOutfieldCheckBox;

    public static BaseballFragment newInstance(UUID playerId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLAYER_ID, playerId);

        BaseballFragment fragment = new BaseballFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //mPlayer = new Player();
        UUID playerId = (UUID) getArguments().getSerializable(ARG_PLAYER_ID);
        mPlayer = PlayerLab.get(getActivity()).getPlayer(playerId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_baseball, container, false);

        mLastName = (EditText)v.findViewById(R.id.baseball_lastName);

        mLastName.addTextChangedListener(new TextWatcher() {
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

        mFirstName = (EditText)v.findViewById(R.id.baseball_firstName);

        mFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                mPlayer.setFirstName(s.toString() + " " + mPlayer.getLastName());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //this one too
            }
        });





        mDateButton = (Button)v.findViewById(R.id.baseball_date);
        mDateButton.setText(mPlayer.getDate().toString());
        mDateButton.setEnabled(false);

//        mPitcherCheckBox = (CheckBox)v.findViewById(R.id.baseball_solved);
//        mPitcherCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                //set the baseball solved property
//                mPlayer.setSolved(isChecked);
//            }
//        });


        mPitcherCheckBox = (CheckBox)v.findViewById(R.id.player_ispitcher);
        mPitcherCheckBox.setChecked(mPlayer.isPitcher());
        mPitcherCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        mCatcherCheckBox = (CheckBox)v.findViewById(R.id.player_iscatchfield);
        mCatcherCheckBox.setChecked(mPlayer.isCatcher());
        mCatcherCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        mInfieldCheckBox = (CheckBox)v.findViewById(R.id.player_isinfield);
        mInfieldCheckBox.setChecked(mPlayer.isInfield());
        mInfieldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        mOutfieldCheckBox = (CheckBox)v.findViewById(R.id.player_isoutfield);
        mOutfieldCheckBox.setChecked(mPlayer.isOutfield());
        mOutfieldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
