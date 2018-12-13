package fr.utt.if26.if26_card;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import android.os.Handler;


import com.google.zxing.WriterException;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.R;
import android.widget.Toast;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.ArrayList;

import static android.graphics.Color.BLACK;


public class CustomCaptureActivity extends AppCompatActivity {

    private CaptureManager mCaptureManager;
    // private CaptureActivityHandler handler = null;

    private BeepManager beepManager;

    private DecoratedBarcodeView mBarcodeView;
    private Toolbar mToolbar;
    private Button manualButton;
    private ArrayList<String> resultList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(fr.utt.if26.if26_card.R.layout.activity_zxing_layout);
        initToolbar();
        mBarcodeView = (DecoratedBarcodeView) findViewById(fr.utt.if26.if26_card.R.id.zxing_barcode_scanner);

        mCaptureManager = new CaptureManager(CustomCaptureActivity.this, mBarcodeView);
        mCaptureManager.initializeFromIntent(getIntent(), savedInstanceState);
        mCaptureManager.decode();

        manualButton = (Button) findViewById(fr.utt.if26.if26_card.R.id.add_manually);
        manualButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(CustomCaptureActivity.this, ManualActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * initialiser window
     */
    private void initWindow() {
        // transparent bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags;
        }
    }

    /**
     * init le toolbar
     */
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(fr.utt.if26.if26_card.R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Scan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCaptureManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCaptureManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCaptureManager.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mCaptureManager.onSaveInstanceState(outState);
    }

    /**
     * 权限处理
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        mCaptureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 按键处理
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mBarcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }


    /*public void handleDecode(Result rawResult, Bundle bundle) {
        //inactivityTimer.onActivity(); //里面包含一个取消的功能

        String text = rawResult.getText();
        if (!TextUtils.isEmpty(text) && !resultList.contains(text)){
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            resultList.add(text);
            beepManager.playBeepSoundAndVibrate();
            Intent intent = new Intent();
            intent.putExtra("number",text);
            setResult(RESULT_OK,intent);

//            if (handler != null) {
//                handler.quitSynchronously();
//                handler = null;
//            }
//            finish();
        }

        if (handler != null) {
            handler.restartPreviewAndDecode();
        }

    }*/




}