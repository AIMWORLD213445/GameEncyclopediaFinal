package com.epicodus.gameencyclopedia.models;


import org.parceler.Parcel;

@Parcel
public class Game {
    private String name;
    private String deck;
    private String imageUrl;
    private String pushId;
    String index;

    public Game() {}

    public Game(String name, String deck, String imageURL) {
        this.index="not specified";
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

    public String getImageUrl() {
        return imageUrl;


    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId)
    {
        this.pushId = pushId;
    }}
