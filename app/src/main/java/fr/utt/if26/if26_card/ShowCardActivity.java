package fr.utt.if26.if26_card;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShowCardActivity extends AppCompatActivity {
    private int cardPosition;
    private ArrayList<Card> cardslist;
    private ImageView cardcode;
    private TextView cardNumber;
    private ImageButton backB;
    private ImageView typecode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        Intent intent=getIntent();
        this.cardPosition = intent.getIntExtra("cardposition",0);
        CardPersistance persistance = new CardPersistance(this, "cards.db", null, 1);
        this.cardslist = persistance.getallCard();

        this.cardcode = (ImageView)findViewById(R.id.card_code);
        this.cardNumber = findViewById(R.id.card_number);
        this.typecode = findViewById(R.id.card_typephoto);
        Card thiscard = this.cardslist.get(this.cardPosition);
        this.cardNumber.setText(thiscard.getNumber());

        this.cardcode.setImageBitmap(thiscard.getCodeImage());
        this.typecode.setImageBitmap(thiscard.getTypeImage());
        this.backB = findViewById(R.id.backB);
        this.backB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ShowCardActivity.this,MainActivity.class);
                i.putExtra("exit",true);
                startActivity(i);
            }
        });
    }
}
