package edu.missouriwestern.awahlert1.csc445.awbaseball;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Austin on 5/5/2017.
 */

public class BaseballPagerActivity extends AppCompatActivity {

    private static final String EXTRA_PLAYER_ID = "edu.missouriwestern.awahlert1.csc445.awbaseball.player_id";

    private ViewPager mViewPager;
    private List<Player> mPlayers;

    public static Intent newIntent(Context packageContext, UUID playerId){
        Intent intent = new Intent(packageContext, BaseballPagerActivity.class);
        intent.putExtra(EXTRA_PLAYER_ID, playerId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseball_pager);

        UUID playerId = (UUID) getIntent().getSerializableExtra(EXTRA_PLAYER_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_baseball_pager_view_pager);

        mPlayers = PlayerLab.get(this).getPlayers();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
               Player player = mPlayers.get(position);
                return BaseballFragment.newInstance(player.getID());
            }

            @Override
            public int getCount() {
                return mPlayers.size();
            }
        });

        for (int i = 0; i < mPlayers.size(); i++){
            if (mPlayers.get(i).getID().equals(playerId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }

}
