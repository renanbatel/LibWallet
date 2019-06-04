package com.renanbatel.libwallet.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.renanbatel.libwallet.R;
import com.renanbatel.libwallet.models.Feature;

import java.util.List;

public class FeaturesAdapter extends RecyclerView.Adapter<FeaturesAdapter.FeaturesViewHolder> {

    private List<Feature> features;
    private OnItemLongClickListener onItemLongClickListener;


    public interface OnItemLongClickListener {
        boolean onItemLongClick( int position, View view );
    }

    public static class FeaturesViewHolder extends RecyclerView.ViewHolder {
        public TextView cardLabelName;

        public FeaturesViewHolder(
            @NonNull View itemView,
            final OnItemLongClickListener onItemLongClickListener
        ) {
            super( itemView );

            this.cardLabelName = itemView.findViewById( R.id.cardLabelName );

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

    public FeaturesAdapter( List<Feature> features ) {
        this.features = features;
    }

    public void setOnItemLongClickListener( OnItemLongClickListener onItemLongClickListener ) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public FeaturesViewHolder onCreateViewHolder( @NonNull ViewGroup viewGroup, int i ) {
        View view = LayoutInflater
            .from( viewGroup.getContext() )
            .inflate(  R.layout.card_feature, viewGroup, false );

        return new FeaturesViewHolder( view, this.onItemLongClickListener );
    }

    @Override
    public void onBindViewHolder( @NonNull FeaturesViewHolder featuresViewHolder, int i ) {
        Feature feature = features.get( i );

        featuresViewHolder.cardLabelName.setText( feature.getTitle() );
    }

    @Override
    public int getItemCount() {

        return features.size();
    }
}
