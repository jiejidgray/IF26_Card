package fr.utt.if26.if26_card;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        TextView tv_sigle = (TextView) v.findViewById(R.id.list_cardName);
        tv_sigle.setText(card.getNumber());

      /*  TextView tv_categorie = (TextView) v.findViewById(R.id.module_tv_categorie);
        tv_categorie.setText(module.getCategorie());

        TextView tv_parcours = (TextView) v.findViewById(R.id.module_tv_parcours);
        tv_parcours.setText(module.getParcours());

        TextView tv_credit = (TextView) v.findViewById(R.id.module_tv_credit);
        tv_credit.setText(Integer.toString(module.getCredit()));*/

        return v;

    }
}
