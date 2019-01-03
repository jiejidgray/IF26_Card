package fr.utt.if26.if26_card;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class CardList {
    private static CardList instance;
    private ArrayList<Card> cardsArray;

    public CardList(){

    }
    public static CardList getInstance() {
        if (instance == null)
            instance = new CardList();
        instance.init();
        return instance;
    }
    public void init() {
        cardsArray = new ArrayList<Card>();

    }
    public void ajoute(Card m) {
        cardsArray.add(m);
    }

    public ArrayList<Card> getCardsArray() {
        System.out.println(cardsArray.toString());
        return cardsArray;
    }
}
