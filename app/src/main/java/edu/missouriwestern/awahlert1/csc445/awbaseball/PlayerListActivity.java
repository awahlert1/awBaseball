package edu.missouriwestern.awahlert1.csc445.awbaseball;

import android.support.v4.app.Fragment;

/**
 * Created by Austin on 4/27/2017.
 */

public class PlayerListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new PlayerListFragment();
    }
}
