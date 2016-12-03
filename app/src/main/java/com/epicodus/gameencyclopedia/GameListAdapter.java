package com.epicodus.gameencyclopedia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/2/16.
 */
public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder> {
    private ArrayList<Game> mGames = new ArrayList<>();
    private Context mContext;

    public GameListAdapter(Context context, ArrayList<Game> games) {
        mContext =context;
        mGames = games;
    }

    public class GameViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.gameImageView) ImageView mGameImageView;
        @Bind(R.id.gameNameTextView) TextView mgameNameTextView;
        @Bind(R.id.gameDeckTextView) TextView mgameDeckTextView;

        private Context mContext;

        public GameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            }
        public void bindGame(Game game) {
            mgameNameTextView.setText(game.getName());
            mgameDeckTextView.setText(game.getDeck());

        }

    }

}
