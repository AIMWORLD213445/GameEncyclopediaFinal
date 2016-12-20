package com.epicodus.gameencyclopedia.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.gameencyclopedia.models.Game;
import com.epicodus.gameencyclopedia.ui.GameDetailActivity;
import com.epicodus.gameencyclopedia.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder> {
    private ArrayList<Game> mGames = new ArrayList<>();
    private Context mContext;

    public GameListAdapter(Context context, ArrayList<Game> games) {
        mContext =context;
        mGames = games;
    }

    @Override
    public GameListAdapter.GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);
        GameViewHolder viewHolder = new GameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameListAdapter.GameViewHolder holder, int position) {
        holder.bindGame(mGames.get(position));
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }


    public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.gameImageView) ImageView mGameImageView;
        @Bind(R.id.gameNameTextView) TextView mgameNameTextView;
        @Bind(R.id.gameDeckTextView) TextView mgameDeckTextView;

        private Context mContext;

        public GameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);

            }
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, GameDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("games", Parcels.wrap(mGames));
            mContext.startActivity(intent);
        }
        public void bindGame(Game game) {
            Picasso.with(mContext).load(game.getImageUrl()).into(mGameImageView);
            mgameNameTextView.setText(game.getName());
            mgameDeckTextView.setText(game.getDeck());

        }

    }

}
