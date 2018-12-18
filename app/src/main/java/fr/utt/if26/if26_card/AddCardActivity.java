package fr.utt.if26.if26_card;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class AddCardActivity extends AppCompatActivity {
        private ArrayList<Card> cards;
        private static Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        bundle = new Bundle();
        Button scan = findViewById(R.id.add_card);
        scan.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(AddCardActivity.this);
                integrator.setCaptureActivity(CustomCaptureActivity.class);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setOrientationLocked(false);
                integrator.setPrompt("Scan");

                integrator.initiateScan();

            }
        });

        final ListView maListe = findViewById(R.id.card_list_lv);

        CardPersistance persistance = new CardPersistance(this, "cards.db", null, 1);
        persistance.initdata();
        this.cards = persistance.getallCard();
        final CardScrollAdaptateur adapteur = new CardScrollAdaptateur(this, R.layout.item_card, cards);
        maListe.setAdapter(adapteur);

        maListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* Intent intent = new Intent(adapteur.getContext(), CardActivity.class);
                intent.putExtra("position", position);
                adapteur.getContext().startActivity(intent);*/
               // bundle.putString("typeName",this.cards.get(1).getTypeName());

                AddCardActivity.bundle.putInt("position",position);
                IntentIntegrator integrator = new IntentIntegrator(AddCardActivity.this);
                integrator.setCaptureActivity(CustomCaptureActivity.class);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(true);
                integrator.setOrientationLocked(false);
                integrator.setPrompt("Scan");
                integrator.initiateScan();
            }
        });




    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "you canceled the scan", Toast.LENGTH_LONG).show();
            } else {

                //二维码的实际内容
                String content = result.getContents().toString();

                //如果你在调用扫码的时候setBarcodeImageEnabled(true)
                //通过下面的方法获取截图的路径
                String imgPath = result.getBarcodeImagePath();

                Intent i = new Intent();
                Intent intent = new Intent();
                intent.setClass(AddCardActivity.this, CardActivity.class);
                intent.putExtra("content",content);
                intent.putExtra("imgPath",imgPath);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } else {
            //Log.d(TAG, "Weird");
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
