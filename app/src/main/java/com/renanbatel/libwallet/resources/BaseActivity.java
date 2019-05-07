package com.renanbatel.libwallet.resources;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.renanbatel.libwallet.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected void setupToolbar( boolean setUpButton ) {
        Toolbar toolbar = findViewById( R.id.toolbar );

        setSupportActionBar( toolbar );

        if ( setUpButton ) {
            ActionBar actionBar = getSupportActionBar();

            actionBar.setDisplayHomeAsUpEnabled( true );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }
}
