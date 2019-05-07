package com.renanbatel.libwallet;

import android.os.Bundle;
import android.widget.TextView;

import com.renanbatel.libwallet.resources.BaseActivity;

public class AboutActivity extends BaseActivity {

    public static final String VERSION = "0.0.1";
    public static final String AUTHOR  = "Renan Batel";

    private TextView labelVersion;
    private TextView labelAuthor;

    private void setupAboutView() {
        String version = String.format( "%1$s %2$s", getString( R.string.label_version ), VERSION );
        String author  = String.format( "%1$s %2$s", getString( R.string.label_author_by ), AUTHOR );

        this.labelVersion = findViewById( R.id.labelVersion );
        this.labelAuthor  = findViewById( R.id.labelAuthor );

        this.labelVersion.setText( version );
        this.labelAuthor.setText( author );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.setupToolbar( true );
        this.setupAboutView();
    }
}
