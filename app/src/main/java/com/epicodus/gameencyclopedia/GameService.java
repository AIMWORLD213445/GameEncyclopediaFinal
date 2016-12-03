package com.epicodus.gameencyclopedia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GameService {

    public static void findGames(String query, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GIANT_BOMB_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.GAME_SEARCH, query);
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.GIANT_BOMB_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);


    }

    public ArrayList<Game> processResults(Response response) {
        ArrayList<Game>games = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject dataJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = dataJSON.getJSONArray("results");
                for (int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject gameJSON = resultsJSON.getJSONObject(i);
                    String name = gameJSON.getString("name");
                    String imageUrl = gameJSON.getString("image");
                    String deck = gameJSON.getString("deck");
                    Game game = new Game(name, imageUrl, deck);
                    games.add(game);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return games;
    }
}