package edu.missouriwestern.awahlert1.csc445.awbaseball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

    private RecyclerView mPlayerRcyclerView;
    private PlayerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        View view = inflater.inflate(R.layout.fragment_player_list, container, false);

        mPlayerRcyclerView = (RecyclerView) view.findViewById(R.id.player_recycler_view);
        mPlayerRcyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
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
            Intent intent = BaseballActivity.newIntent(getActivity(), mPlayer.getID());
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
