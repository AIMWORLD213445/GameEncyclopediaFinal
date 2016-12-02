package com.epicodus.gameencyclopedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GameActivity extends AppCompatActivity {
    public static final String TAG = GameActivity.class.getSimpleName();


    private void getGames(String query) {
        final GameService gameService = new GameService();
       gameService.findGames(query, new Callback()  {

           @Override
           public void onFailure(Call call, IOException e) {
               e.printStackTrace();
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               try {
                   String jsonData = response.body().string();
                   Log.v(TAG, jsonData);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        });
    }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game);
            ButterKnife.bind(this);
            Intent intent = getIntent();
            String query = intent.getStringExtra("query");
            getGames(query);


        }
    }
