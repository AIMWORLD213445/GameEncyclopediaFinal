package com.epicodus.gameencyclopedia;



public class Game {
    private String name;
    private String deck;
    private String imageUrl;

    public Game() {}

    public Game(String name, String deck, String imageURL) {
        this.name = name;
        this.deck = deck;
        this.imageUrl = imageURL;
    }

    public String getName() {
        return name;
    }

    public String getDeck() {
        return deck;
    }

    public String getmImageUrl() {
        return imageUrl;


    }
}
