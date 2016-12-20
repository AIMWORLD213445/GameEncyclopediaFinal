package com.epicodus.gameencyclopedia.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.gameencyclopedia.Constants;
import com.epicodus.gameencyclopedia.models.Game;
import com.epicodus.gameencyclopedia.ui.GameDetailActivity;
import com.epicodus.gameencyclopedia.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseGameViewHolder extends RecyclerView.ViewHolder {
    public ImageView mGameImageView;
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;


    View mView;
    Context mContext;

    public FirebaseGameViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindGame(Game game) {
        mGameImageView = (ImageView) mView.findViewById(R.id.gameImageView);

//        ImageView gameImageView = (ImageView) mView.findViewById(R.id.gameImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.gameNameTextView);
        TextView deckTextView = (TextView) mView.findViewById(R.id.gameDeckTextView);

        Picasso.with(mContext).load(game.getImageUrl()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(mGameImageView);

        nameTextView.setText(game.getName());
        deckTextView.setText(game.getDeck());


    }

}