package com.renanbatel.libwallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.renanbatel.libwallet.resources.BaseActivity;

public class PreferencesActivity extends BaseActivity {

    public static final String FILE_KEY      = "com.renanbatel.libwallet_PREFERENCE_FILE";
    public static final String SHOW_COUNTERS = "SHOW_COUNTERS";

    private CheckBox checkBoxShowCounter;
    private SharedPreferences sharedPreferences;

    private void setupHomePreferences() {
        this.checkBoxShowCounter = findViewById( R.id.checkBoxShowCounters );
        this.sharedPreferences   = this.getSharedPreferences( FILE_KEY, Context.MODE_PRIVATE );

        Boolean showCounters = sharedPreferences.getBoolean( SHOW_COUNTERS, true );

        this.checkBoxShowCounter.setChecked( showCounters );

        this.checkBoxShowCounter.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean( SHOW_COUNTERS, checkBoxShowCounter.isChecked() );
                editor.commit();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        this.setupToolbar( true );
        this.setupHomePreferences();
    }
}
