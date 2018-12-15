package fr.utt.if26.if26_card;

import java.util.ArrayList;

public interface PersistanceInterface {

    public void addCard(Card c);

    public void initdata();

    public void delCard(Card c);

    public void updateCard(Card c);

    public Card getCard(String key);

    public int countCard();

    public ArrayList<Card> getallCard();

}
