package edu.missouriwestern.awahlert1.csc445.awbaseball;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Austin on 4/27/2017.
 */

public class PlayerLab {

    private static PlayerLab sPlayerLab;

    private List<Player> mPlayers;

    public static PlayerLab get(Context context){
        if(sPlayerLab == null){
            sPlayerLab = new PlayerLab(context);
        }
        return sPlayerLab;
    }

    private PlayerLab(Context context){
        mPlayers = new ArrayList<>();

        for (int i = 0; i < 51; i++){
            Player player = new Player();
            player.setFirstName("First Name Here " + i);
            player.setOutfield(true); //every other one
            mPlayers.add(player);
        }

    }

    public List<Player> getPlayers(){
        return mPlayers;
    }

    public Player getPlayer(UUID id){
        for (Player player : mPlayers){
            if(player.getID().equals(id)){
                return player;
            }
        }
        return null;
    }

}