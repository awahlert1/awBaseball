package edu.missouriwestern.awahlert1.csc445.awbaseball;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private void updateUI(){
        PlayerLab playerLab = PlayerLab.get(getActivity());
        List<Player> players = playerLab.getPlayers();

        mAdapter = new PlayerAdapter(players);
        mPlayerRcyclerView.setAdapter(mAdapter);
    }

    private class PlayerHolder extends RecyclerView.ViewHolder{
        public TextView mTitleTextView;

        public PlayerHolder(View itemView){
            super(itemView);

            mTitleTextView = (TextView) itemView;
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
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new PlayerHolder(view);
        }

        @Override
        public void onBindViewHolder(PlayerHolder holder, int position){
            Player player = mPlayers.get(position);
            holder.mTitleTextView.setText(player.getFirstName());
        }

        @Override
        public int getItemCount(){
            return mPlayers.size();
        }

    }

}
