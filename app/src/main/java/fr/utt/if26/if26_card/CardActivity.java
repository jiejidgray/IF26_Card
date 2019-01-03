package fr.utt.if26.if26_card;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.ArrayList;

import static android.graphics.Color.BLACK;


public class CardActivity extends AppCompatActivity{

    private ImageView mResultImage;
    private TextView mResultText;
    private TextView mResultTypeName;
    private Button save;
    private CardPersistance persistance;
    public  Bitmap photo;
    public String ctype;
    private ArrayList<Card> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

         persistance = new CardPersistance(this, "cards.db", null, 1);
        persistance.initdata();
        cards = persistance.getallCard();

        Intent i =  getIntent();
        mResultImage = (ImageView) findViewById(R.id.code_img2);
        mResultText = (TextView) findViewById(R.id.result_text);
        mResultTypeName = (TextView)findViewById(R.id.result_typeName);

        mResultText.setText(i.getStringExtra("content"));
        if(i.getIntExtra("position",0) == 100){
            mResultTypeName.setText("Other type card");
        }else {
            mResultTypeName.setText(cards.get(i.getIntExtra("position",0)).getTypeName());
        }

        Bitmap bitmap = (Bitmap) i.getParcelableExtra("bp");
        mResultImage.setImageBitmap(bitmap);
        String type = mResultTypeName.getText().toString();

        Log.i("type","1234"+type);

        if( type.contains("Sephora")) {
            Bitmap photoS=BitmapFactory.decodeResource(getResources(), R.drawable.sephora);
            //this.newCard.setNumber(this.mResultText.getText().toString());
           // i.putExtra("pho",photoS);
            Log.i("type","123"+type);
            photo=photoS;
            ctype = type;
           // this.newCard.setCodephoto(bitmap);
           // newCard.setTypephoto(photoS);
           // newCard.setTypeName(this.mResultTypeName.getText().toString());
        }else if(type.contains("Darty")){
             photo=BitmapFactory.decodeResource(getResources(), R.drawable.darty);
            ctype = type;

            // i.putExtra("pho",photoD);

           // newCard = new Card(mResultText.getText().toString(),bitmap,photoD,null,mResultTypeName.getText().toString());
        }else if(type.contains("Carrefour")){
            photo=BitmapFactory.decodeResource(getResources(), R.drawable.carrefour);
            ctype = type;

            // i.putExtra("pho",photoC);
            //newCard = new Card(mResultText.getText().toString(),bitmap,photoC,null,mResultTypeName.getText().toString());
        }else if(type.contains("Fnac")) {
               photo=BitmapFactory.decodeResource(getResources(), R.drawable.fnac);
            ctype = type;

            //i.putExtra("pho",photoF);
            //newCard = new Card(mResultText.getText().toString(),bitmap,photoF,null,mResultTypeName.getText().toString());
        }


        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  getIntent();
                Bitmap bitmap = (Bitmap) i.getParcelableExtra("bp");
               // Bitmap photo = (Bitmap)i.getParcelableExtra("pho");
                persistance = new CardPersistance(CardActivity.this, "cards.db", null, 1);
                Card test = new Card(i.getStringExtra("content"),bitmap,photo,null,ctype);

                persistance.addCard(test);
                CardList.getInstance().ajoute(test);
                //Toast.makeText(CardActivity.this, "Add réussi", Toast.LENGTH_LONG).show();
                Intent intent = new Intent (CardActivity.this, MainActivity.class);
                intent.putExtra("exit",true);

                startActivity(intent);
            }
        });
    }


    /**
     *
     * @param content content
     * @return return barcode img
     * @throws WriterException WriterException
     */
    public Bitmap CreateOneDCode(String content) throws WriterException {
        BitMatrix matrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.CODE_128, 500, 200);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                }
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }








}
