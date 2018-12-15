package fr.utt.if26.if26_card;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Card {
    //顺序：卡号，条形码图，卡片种类图，注释
    private String Number;
    private Bitmap Codephoto;
    private Bitmap Typephoto;
    private String Comment;

    public Card(String Number, Bitmap Codephoto, Bitmap Typephoto, String Comment) {
        this.Number = Number;
        this.Codephoto = Codephoto;
        this.Typephoto = Typephoto;
        this.Comment = Comment;

    }






}
