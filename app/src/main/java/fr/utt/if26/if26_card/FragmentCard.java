package fr.utt.if26.if26_card;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

public class FragmentCard extends Fragment {
    private String content;
    private GridView gridview;


    public FragmentCard (String content) {
        this.content = content;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        View view1 = inflater.inflate(R.layout.activity_card_liste,container,false);
        gridview = (GridView) view.findViewById(R.id.liste_card_lv);
        CardPersistance persistance = new CardPersistance(this, "cards.db", null, 1);
        List<Card> listcard = persistance
        txt_content.setText(content);
        return view;
    }
}
