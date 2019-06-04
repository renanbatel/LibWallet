package com.renanbatel.libwallet.daos;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.renanbatel.libwallet.models.Library;

@Database(
    entities = {
        Library.class
    },
    version = 1
)
public abstract class LibWalletDatabase extends RoomDatabase {

    private static LibWalletDatabase instance;

    public abstract LibraryDAO libraryDao();

    public static LibWalletDatabase getDatabase( final Context context ) {
        if( instance == null ) {
            synchronized ( LibWalletDatabase.class ) {
                if ( instance == null ) {
                    instance = Room
                        .databaseBuilder(
                            context,
                            LibWalletDatabase.class,
                            "libwallet.db"
                        )
                        .allowMainThreadQueries()
                        .build();
                }
            }
        }

        return instance;
    }
}
