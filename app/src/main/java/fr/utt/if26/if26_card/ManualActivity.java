package fr.utt.if26.if26_card;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ManualActivity extends AppCompatActivity {


    // content of barcode
    private TextView cardNumberInput;
    // create barcode button
    private Button convertBarCode;
    private Spinner spinner;
    private ArrayAdapter <String> adapter;
    private List<String> list;
    private static Bundle bunle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        spinner = findViewById(R.id.spinner);
        spinner.setPrompt("Choose your card type");
        initDatas();
        bunle = new Bundle();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        spinner.setAdapter(adapter);
        cardNumberInput = (TextView)findViewById(R.id.cardNum);
        convertBarCode = (Button)findViewById(R.id.bitmap);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 4){
                    ManualActivity.bunle.putInt("position",100);
                }else {
                    ManualActivity.bunle.putInt("position",position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ManualActivity.bunle.putInt("position",100);
            }
        });
        setListener();

    }

    private void setListener() {
        convertBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String str = cardNumberInput.getText().toString().trim();
                int size = str.length();
                for (int i = 0; i < size; i++) {
                    int c = str.charAt(i);
                    if ((19968 <= c && c < 40623)) {
                        Toast.makeText(ManualActivity.this, "Must enter card number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                Bitmap bmp = null;
                try {
                    if (str != null && !"".equals(str)) {
                        bmp = CreateOneDCode(str);
                    }
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                if (bmp != null) {
                    Intent intent = new Intent();
                    intent.setClass(ManualActivity.this, CardActivity.class);
                    intent.putExtra("bp", bmp);
                    intent.putExtra("content",cardNumberInput.getText().toString());
                    intent.putExtras(bunle);
                    startActivity(intent);
                }
            }
        });


    }
    private void initDatas() {
        list=new ArrayList<String>();
        list.add("Sephora");
        list.add("Darty");
        list.add("Carrefour");
        list.add("Fnac");
        list.add("Other type card");
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
