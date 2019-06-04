package com.renanbatel.libwallet.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.renanbatel.libwallet.R;
import com.renanbatel.libwallet.models.Library;

import java.util.List;

public class LibrariesAdapter extends RecyclerView.Adapter<LibrariesAdapter.LibrariesViewHolder> {

    private List<Library> libraries;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public interface OnItemClickListener {
        void onItemClick( int position );
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick( int position, View view );
    }

    public static class LibrariesViewHolder extends RecyclerView.ViewHolder {
        public TextView cardLabelCode;
        public TextView cardLabelName;
        public TextView cardLabelFeatures;

        public LibrariesViewHolder(
            @NonNull View itemView,
            final OnItemClickListener onItemClickListener,
            final OnItemLongClickListener onItemLongClickListener
        ) {
            super( itemView );

            this.cardLabelCode     = itemView.findViewById( R.id.cardLabelCode );
            this.cardLabelName     = itemView.findViewById( R.id.cardLabelName );
            this.cardLabelFeatures = itemView.findViewById( R.id.labelFeatures);

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( onItemClickListener != null ) {
                        int position = getAdapterPosition();

                        if ( position != RecyclerView.NO_POSITION ) {
                            onItemClickListener.onItemClick( position );
                        }
                    }
                }
            } );
            itemView.setOnLongClickListener( new View.OnLongClickListener() {
                @Override
                public boolean onLongClick( View v ) {
                    if ( onItemLongClickListener != null ) {
                        int position = getAdapterPosition();

                        if ( position != RecyclerView.NO_POSITION ) {
                            onItemLongClickListener.onItemLongClick( position, v );
                        }
                    }

                    return true;
                }
            });
        }
    }

    public LibrariesAdapter( List<Library> libraries ) {
        this.libraries = libraries;
    }

    public void setOnItemClickListener( OnItemClickListener onItemClickListener ) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener( OnItemLongClickListener onItemLongClickListener ) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public LibrariesViewHolder onCreateViewHolder( @NonNull ViewGroup viewGroup, int i ) {
        View view = LayoutInflater
            .from( viewGroup.getContext() )
            .inflate(  R.layout.card_library, viewGroup, false );

        return new LibrariesViewHolder( view, this.onItemClickListener, this.onItemLongClickListener );
    }

    @Override
    public void onBindViewHolder( @NonNull LibrariesViewHolder librariesViewHolder, int i ) {
        Library library = libraries.get( i );

        librariesViewHolder.cardLabelCode.setText( library.getLanguage() );
        librariesViewHolder.cardLabelName.setText( library.getName() );
        librariesViewHolder.cardLabelFeatures.setText( library.getFeatures() );
    }

    @Override
    public int getItemCount() {

        return libraries.size();
    }
}
