package com.renanbatel.libwallet.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(
    tableName = "features",
    foreignKeys = {
        @ForeignKey(
            entity = Library.class,
            parentColumns = "id",
            childColumns = "libraryId",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Feature {

    @PrimaryKey( autoGenerate = true )
    private Long id;
    private String title;
    @ColumnInfo( index = true )
    private Long libraryId;

    public Feature(
        Long id,
        String title,
        Long libraryId
    ) {
        this.id        = id;
        this.title     = title;
        this.libraryId = libraryId;
    }

    @Ignore
    public Feature(
        String title,
        Long libraryId
    ) {
        this.title     = title;
        this.libraryId = libraryId;
    }

    @Ignore
    public Feature(
        String title
    ) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId( Long libraryId ) {
        this.libraryId = libraryId;
    }
}
