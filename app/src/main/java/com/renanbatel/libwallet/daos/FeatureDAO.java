package com.renanbatel.libwallet.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.renanbatel.libwallet.models.Feature;

import java.util.List;

@Dao
public interface FeatureDAO {

    @Insert
    public Long insert( Feature feature );

    @Delete
    public void delete( Feature feature );

    @Update
    public void update( Feature feature );

    @Query( "SELECT * FROM features WHERE libraryId = :libraryId" )
    public List<Feature> getAllFromLibrary( Long libraryId );
}
