package edu.missouriwestern.awahlert1.csc445.awbaseball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Austin on 4/27/2017.
 */

public class PlayerListFragment extends Fragment {

    private static final String SAVED_SUBTITE_VISIBLE = "subtitle";
    private RecyclerView mPlayerRcyclerView;
    private PlayerAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        View view = inflater.inflate(R.layout.fragment_player_list, container, false);

        mPlayerRcyclerView = (RecyclerView) view.findViewById(R.id.player_recycler_view);
        mPlayerRcyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(saveInstanceState != null){
            mSubtitleVisible = saveInstanceState.getBoolean(SAVED_SUBTITE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_player_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if(mSubtitleVisible){
            subtitleItem.setTitle(R.string.hide_subtitle);
        }else{
            subtitleItem.setTitle(R.string.show_subtitle);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item_new_player:
                Player player = new Player();
                PlayerLab.get(getActivity()).addPlayer(player);
                Intent intent = BaseballPagerActivity.newIntent(getActivity(), player.getID());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle(){
        PlayerLab playerLab = PlayerLab.get(getActivity());
        int playerCount = playerLab.getPlayers().size();
        String subtitle = getString(R.string.subtitle_format, playerCount);

        if(!mSubtitleVisible){
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI(){
        PlayerLab playerLab = PlayerLab.get(getActivity());
        List<Player> players = playerLab.getPlayers();

        if(mAdapter == null) {
            mAdapter = new PlayerAdapter(players);
            mPlayerRcyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    private class PlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

       // public TextView mTitleTextView;

        private Player mPlayer;

        public void bindPlayer(Player player){
            mPlayer = player;
            mTitleTextView.setText(mPlayer.getFirstName());
            mDateTextView.setText(mPlayer.getDate().toString());
            mPitcherCheckBox.setChecked(mPlayer.isPitcher());
            mCatcherCheckBox.setChecked(mPlayer.isCatcher());
            mInfieldCheckBox.setChecked(mPlayer.isInfield());
            mOutfieldCheckBox.setChecked(mPlayer.isOutfield());
        }

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mPitcherCheckBox;
        private CheckBox mCatcherCheckBox;
        private CheckBox mInfieldCheckBox;
        private CheckBox mOutfieldCheckBox;

        public PlayerHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

           // mTitleTextView = (TextView) itemView;

            mTitleTextView = (TextView) itemView.findViewById(R.id.player_list_item_titleTextView);
            mDateTextView = (TextView) itemView.findViewById(R.id.player_list_item_dateTextView);
            mPitcherCheckBox = (CheckBox) itemView.findViewById(R.id.player_list_item_pitcherCheckBox);
            mCatcherCheckBox = (CheckBox) itemView.findViewById(R.id.player_list_item_catcherCheckBox);
            mInfieldCheckBox = (CheckBox) itemView.findViewById(R.id.player_list_item_infieldCheckBox);
            mOutfieldCheckBox = (CheckBox) itemView.findViewById(R.id.player_list_item_outfieldCheckBox);

        }

        @Override
        public void onClick(View v){
            Toast.makeText(getActivity(), mPlayer.getFirstName() + " clicked!", Toast.LENGTH_SHORT).show();
            //Intent intent = BaseballActivity.newIntent(getActivity(), mPlayer.getID());
            Intent intent = BaseballPagerActivity.newIntent(getActivity(), mPlayer.getID());
            startActivity(intent);
        }

    }

    private class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder>{

        private List<Player> mPlayers;

        public PlayerAdapter(List<Player> players){
            mPlayers = players;
        }

        @Override
        public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_player, parent, false);
            return new PlayerHolder(view);
        }

        @Override
        public void onBindViewHolder(PlayerHolder holder, int position){
            Player player = mPlayers.get(position);
        //    holder.mTitleTextView.setText(player.getFirstName());
            holder.bindPlayer(player);
        }

        @Override
        public int getItemCount(){
            return mPlayers.size();
        }

    }

}
