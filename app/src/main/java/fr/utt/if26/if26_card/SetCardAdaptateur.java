package fr.utt.if26.if26_card;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class SetCardAdaptateur extends ArrayAdapter<Card>  {

    private ArrayList<Card> cards;
    private Context contexte;
    private  int ressource;


    public SetCardAdaptateur(Context context,  int resource, ArrayList<Card> data) {
        super(context,resource, data);
        this.cards = data;
        this.contexte = context;
        this.ressource = resource;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) contexte).getLayoutInflater();
        View v = inflater.inflate(ressource, parent, false);

        Card card = cards.get(position);
        ImageView iv_card = v.findViewById(R.id.image);
        iv_card.setImageBitmap(card.getTypeImage());


        return v;
    }
}
