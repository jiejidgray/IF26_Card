package fr.utt.if26.if26_card;

import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private android.app.FragmentManager fManager;
    private FragmentCard fg_card;
    private FragmentReduction fg_reduction;
    private FragmentSetting fg_setting;

    private TextView txt_topbar;
    private TextView txt_card;
    private TextView txt_reduction;
    private TextView txt_setting;
    private FrameLayout content;
    private Button ajouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        CardPersistance persistance = new CardPersistance(this, "cards.db", null, 1);
        persistance.onUpgrade(persistance.getReadableDatabase(),1,1);
        ArrayList<Card> cardlist = new ArrayList<Card>();
        // Resources res = this..getResources();
        //  Bitmap  photoS = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        // Drawable Draw =  res.getDrawable(R.drawable.sephora, null);

        Bitmap photoS=BitmapFactory.decodeResource(getResources(), R.drawable.sephora);



        //Bitmap photoS = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/mipmap-xhdpi/sephora.png"));
        Card cardSephora = new Card("66789985432",photoS, photoS,"Card Sephora comment !","Sephora");
        Bitmap photoD=BitmapFactory.decodeResource(getResources(), R.drawable.darty);

        //Bitmap photoD = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/mipmap-xhdpi/darty.jpg"));
        Card cardDarty = new Card("96289266701",photoD,photoD,"Card Darty comment !","Darty");

        Bitmap photoC=BitmapFactory.decodeResource(getResources(), R.drawable.carrefour);
        //Bitmap photoC = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/mipmap-xhdpi/carrefour.jpg"));
        Card cardCarrefour = new Card("557845899854",photoC,photoC,"Card Carrefour comment !","Carrefour");

        Bitmap photoF=BitmapFactory.decodeResource(getResources(), R.drawable.fnac);
        //Bitmap photoF = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/mipmap-xhdpi/fnac.png"));
        Card cardFnac = new Card("7608765435",photoF,photoF,"Card Fnac comment !","Fnac");

        cardlist.add(cardSephora);
        cardlist.add(cardDarty);
        cardlist.add(cardCarrefour);
        cardlist.add(cardFnac);
        Iterator iterateur = cardlist.iterator();
        while (iterateur.hasNext()) {
            Card c = (Card) (iterateur.next());
            persistance.addCard(c);
        }


        setContentView(R.layout.activity_main);

        Button ajoutebutton= findViewById(R.id.button_ajouter);
        ajoutebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AddCardActivity.class);
                startActivity(intent);
            }
            });
        fManager = getFragmentManager();
        bindViews();
        txt_card.performClick();

    }
//关联
    private void bindViews() {
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        txt_card = (TextView) findViewById(R.id.txt_card);
        txt_reduction = (TextView) findViewById(R.id.txt_reduction);
        txt_setting = (TextView) findViewById(R.id.txt_setting);

        content = (FrameLayout) findViewById(R.id.fgcontent);
        ajouter = (Button)findViewById(R.id.button_ajouter);

        txt_card.setOnClickListener(this);
        txt_reduction.setOnClickListener(this);
        txt_setting.setOnClickListener(this);
    }

//三个text设为不可选状态
    private void setSelected(){
        txt_card.setSelected(false);
        txt_reduction.setSelected(false);
        txt_setting.setSelected(false);
    }
//隐藏所有fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg_card != null){
            fragmentTransaction.hide(fg_card);

        }

        if(fg_reduction != null)fragmentTransaction.hide(fg_reduction);
        if(fg_setting != null)fragmentTransaction.hide(fg_setting);
    }



//切换fragment的三个case

    @Override
    public void onClick(View v) {

        FragmentTransaction fTransaction = fManager.beginTransaction();
       // hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.txt_card:
                setSelected();
                txt_card.setSelected(true);
                if(fg_card == null){
                    fg_card = new FragmentCard();
                    fTransaction.add(R.id.fgcontent,fg_card);
                    //ajouter.setVisibility(View.VISIBLE);
                    //txt_topbar.setText(R.string.card);
                }
                    //fTransaction.show(fg_card);
                    fTransaction.replace(R.id.fgcontent, fg_card);
                    //fTransaction.addToBackStack(null);
                    ajouter.setVisibility(View.VISIBLE);
                    txt_topbar.setText(R.string.card);

                break;
            case R.id.txt_reduction:
                setSelected();
                txt_reduction.setSelected(true);
                if(fg_reduction == null){
                    fg_reduction = new FragmentReduction();
                    fTransaction.add(R.id.fgcontent,fg_reduction);
                  //  ajouter.setVisibility(View.GONE);
                  //  txt_topbar.setText(R.string.reduction);
                }
                    //fTransaction.show(fg_reduction);
                    fTransaction.replace(R.id.fgcontent, fg_reduction);
                   // fTransaction.addToBackStack(null);
                    ajouter.setVisibility(View.GONE);
                    txt_topbar.setText(R.string.reduction);

                break;
            case R.id.txt_setting:
                setSelected();
                txt_setting.setSelected(true);
                if(fg_setting == null){
                    fg_setting = new FragmentSetting();
                    fTransaction.add(R.id.fgcontent,fg_setting);
                  //  ajouter.setVisibility(View.GONE);
                   // txt_topbar.setText(R.string.setting);
                }
                    fTransaction.replace(R.id.fgcontent, fg_setting);
                  //  fTransaction.addToBackStack(null);
                    ajouter.setVisibility(View.GONE);
                    txt_topbar.setText(R.string.setting);
                   // fTransaction.show(fg_setting);

                break;

        }
        fTransaction.commit();
    }

}
