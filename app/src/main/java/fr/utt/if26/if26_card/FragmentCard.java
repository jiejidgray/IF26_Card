package fr.utt.if26.if26_card;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FragmentCard extends Fragment {
    //private String content;
    private GridView gridview;

  //  private static String ARG_PARAM = "param_key";
   // private String mParam;
  //  private Activity mActivity;


   /* //不确定需不需要的功能，之后可能删掉
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mParam = getArguments().getString(ARG_PARAM);  //获取参数

    }
    //不确定需不需要的功能，之后可能删掉
    public static FragmentCard newInstance (String str) {
        FragmentCard frag = new FragmentCard();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);   //设置参数
        return frag;
        }
*/
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_card_liste,container,false);
//怎么给每个button加onclicklistener？？
/*
        imgbt = (ImageButton) view.findViewById(R.id.image);
        imgbt.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });*/
        gridview = (GridView) view.findViewById(R.id.liste_card_lv);

        CardPersistance persistance = new CardPersistance(this.getActivity(), "cards.db", null, 1);
        ArrayList<Card> listcard = persistance.getallCard();
        SetCardAdaptateur adapteur = new SetCardAdaptateur(this.getActivity(), R.layout.card_icon, listcard);
        gridview.setAdapter(adapteur);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),ShowCardActivity.class);
                intent.putExtra("cardposition",i);
                startActivity(intent);
            }
        });
        return view;
    }
}
