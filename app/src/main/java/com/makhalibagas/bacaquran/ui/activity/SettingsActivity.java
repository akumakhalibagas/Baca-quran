package com.makhalibagas.bacaquran.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.ui.fragment.AboutFragment;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.bt_back).setOnClickListener(this);
        findViewById(R.id.donasi).setOnClickListener(this);
        findViewById(R.id.blog).setOnClickListener(this);
        findViewById(R.id.about).setOnClickListener(this);
        findViewById(R.id.github).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_back:
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                break;
            case R.id.donasi:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/+6282313666691")));
                break;

            case R.id.blog:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://makhalibagas.blogspot.com/")));
                break;

            case R.id.about:
                Fragment fragment = new AboutFragment();
                loadFragment(fragment);
                findViewById(R.id.toolbar).setVisibility(View.INVISIBLE);
                findViewById(R.id.donasi).setVisibility(View.INVISIBLE);
                findViewById(R.id.blog).setVisibility(View.INVISIBLE);
                findViewById(R.id.about).setVisibility(View.INVISIBLE);
                findViewById(R.id.github).setVisibility(View.INVISIBLE);
                break;

            case R.id.github:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/makhalibagas")));
                break;

        }

    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }
}
