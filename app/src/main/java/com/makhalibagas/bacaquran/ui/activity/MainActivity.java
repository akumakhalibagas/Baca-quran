package com.makhalibagas.bacaquran.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.ui.fragment.menuBottom.FavoriteFragment;
import com.makhalibagas.bacaquran.ui.fragment.menuBottom.HomeFragment;
import com.makhalibagas.bacaquran.ui.fragment.menuBottom.LastReadFragment;
import com.makhalibagas.bacaquran.ui.fragment.menuBottom.RecommendFragment;

/**
 * Created by Bagas Makhali
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottom_navigation);
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        loadFragment(new HomeFragment());

    }


    private BottomNavigationViewEx.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_last:
                    fragment = new LastReadFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_favorite:
                    fragment = new FavoriteFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_rekomendasi:
                    fragment = new RecommendFragment();
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
