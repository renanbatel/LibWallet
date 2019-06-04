package com.renanbatel.libwallet.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(
    tableName = "libraries"
)
public class Library implements Parcelable {

    @PrimaryKey( autoGenerate = true )
    private Long id;
    private String name;
    private String language; // TODO: List<Language>
    private String features; // TODO: List<Feature>
    private String details;
    private String repositories; // TODO: List<String>

    public static final Parcelable.Creator<Library> CREATOR = new Parcelable.Creator<Library>(){

        @Override
        public Library createFromParcel( Parcel parcel ) {
            return new Library( parcel );
        }

        @Override
        public Library[] newArray( int size ) {
            return new Library[ 0 ];
        }
    };


    public Library(
        Long id,
        String name,
        String language,
        String features,
        String details,
        String repositories
    ) {
        this.id           = id;
        this.name         = name;
        this.language     = language;
        this.features     = features;
        this.details      = details;
        this.repositories = repositories;
    }

    @Ignore
    public Library(
        String name,
        String language,
        String features,
        String details,
        String repositories
    ) {
        this.setName( name );
        this.setLanguage( language );
        this.setFeatures( features );
        this.setDetails( details );
        this.setRepositories( repositories );
    }

    @Ignore
    public Library( Parcel parcel ) {
        this.id           = parcel.readLong();
        this.name         = parcel.readString();
        this.language     = parcel.readString();
        this.features     = parcel.readString();
        this.details      = parcel.readString();
        this.repositories = parcel.readString();
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ) {
        dest.writeLong( this.id );
        dest.writeString( this.name );
        dest.writeString( this.language );
        dest.writeString( this.features );
        dest.writeString( this.details );
        dest.writeString( this.repositories );
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage( String language ) {
        this.language = language;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures( String features ) {
        this.features = features;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails( String details ) {
        this.details = details;
    }

    public String getRepositories() {
        return repositories;
    }

    public void setRepositories( String repositories ) {
        this.repositories = repositories;
    }
}
