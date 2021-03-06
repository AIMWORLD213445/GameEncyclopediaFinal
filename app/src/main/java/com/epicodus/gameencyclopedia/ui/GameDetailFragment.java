package com.epicodus.gameencyclopedia.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.gameencyclopedia.Constants;
import com.epicodus.gameencyclopedia.R;
import com.epicodus.gameencyclopedia.models.Game;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;


public class GameDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.gameImageView) ImageView mImageView;
    @Bind(R.id.gameNameTextView) TextView mNameView;
    @Bind(R.id.deckTextView) TextView mDescView;
    @Bind(R.id.saveGameButton) Button mSaveGameButton;

    private Game mGame;


    public static GameDetailFragment newInstance(Game game) {
        GameDetailFragment gameDetailFragment = new GameDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("game", Parcels.wrap(game));
        gameDetailFragment.setArguments(args);
        return gameDetailFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGame = Parcels.unwrap(getArguments().getParcelable("game"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mGame.getImageUrl()).into(mImageView);

        mNameView.setText(mGame.getName());
        mDescView.setText(mGame.getDeck());

        mSaveGameButton.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == mSaveGameButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference gameRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_GAMES)
                    .child(uid);

            DatabaseReference pushRef = gameRef.push();
            String pushId = pushRef.getKey();
            mGame.setPushId(pushId);
            pushRef.setValue(mGame);
            Toast.makeText(getContext(), "Add to buylist", Toast.LENGTH_SHORT).show();

        }

    }
}
