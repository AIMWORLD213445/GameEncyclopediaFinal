package com.epicodus.gameencyclopedia;

public class Game {
    private String mName;
    private String mDeck;
    private String mImageUrl;

    public Game(String name, String deck, String imageURL) {
        this mName = name;
        this mDeck = deck;
        this mImageUrl = imageURL;
    }

    public String getmName() {
        return mName;
    }
    public String getDeck() {
        return mDeck;
    }
    public String getmImageUrl() {
        return mImageUrl;


}
