package fr.utt.if26.if26_card;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardScrollAdaptateur extends ArrayAdapter {
    ArrayList<Card> cards;
    Context contexte;
    int ressource;

    public CardScrollAdaptateur(Context context, int resource, ArrayList<Card> data) {
        super(context, resource, data);
        this.cards = data;
        this.contexte = context;
        this.ressource = resource;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) contexte).getLayoutInflater();
        View v = inflater.inflate(ressource, parent, false);

        Card card = cards.get(position);

        ImageView tv_typeImage = (ImageView) v.findViewById(R.id.list_icon);
        tv_typeImage.setImageBitmap(card.getTypeImage());

        TextView tv_cardName = (TextView) v.findViewById(R.id.list_cardName);
        tv_cardName.setText(card.getTypeName());



        return v;

    }


}
