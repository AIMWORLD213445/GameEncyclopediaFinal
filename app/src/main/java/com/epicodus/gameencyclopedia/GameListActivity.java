package com.epicodus.gameencyclopedia;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GameListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mRecentSearch;
    public static final String TAG = GameListActivity.class.getSimpleName();
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private GameListAdapter mAdapter;

    public ArrayList<Game> mGames = new ArrayList<>();

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentSearch = mSharedPreferences.getString(Constants.PREFERENCES_QUERY_KEY, null);
        if (mRecentSearch != null) {
            getGames((mRecentSearch));
        }

//        getGames(query);


    }


    private void getGames(String query) {
        final GameService gameService = new GameService();

        gameService.findGames(query, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mGames = gameService.processResults(response);

                GameListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new GameListAdapter(getApplicationContext(), mGames);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(GameListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }

                });
            }

            ;
        });



    }
}
