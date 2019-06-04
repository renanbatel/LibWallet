package com.renanbatel.libwallet.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.renanbatel.libwallet.models.Library;

import java.util.List;

@Dao
public interface LibraryDAO {

    @Insert
    public Long insert( Library library );

    @Delete
    public void delete( Library library );

    @Update
    public void update( Library library );

    @Query( "DELETE FROM libraries" )
    public void clean();

    @Query( "SELECT * FROM libraries" )
    public List<Library> list();
}
