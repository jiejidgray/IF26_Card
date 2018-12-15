package fr.utt.if26.if26_card;

import android.app.FragmentTransaction;
import android.content.Intent;

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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private android.app.FragmentManager fManager;
    private MyFragment fg_card,fg_reduction,fg_setting;

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
        content = (FrameLayout) findViewById(R.id.content);
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
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.txt_card:
                setSelected();
                txt_card.setSelected(true);
                if(fg_card == null){
                    fg_card = new MyFragment("第一个Fragment");
                    fTransaction.add(R.id.content,fg_card);
                    ajouter.setVisibility(View.VISIBLE);
                    txt_topbar.setText(R.string.card);
                }else{
                    //fTransaction.show(fg_card);
                    fTransaction.replace(R.id.content, fg_card);
                    //fTransaction.addToBackStack(null);
                    ajouter.setVisibility(View.VISIBLE);
                    txt_topbar.setText(R.string.card);
                }
                break;
            case R.id.txt_reduction:
                setSelected();
                txt_reduction.setSelected(true);
                if(fg_reduction == null){
                    fg_reduction = new MyFragment("第二个Fragment");
                    fTransaction.add(R.id.content,fg_reduction);
                    ajouter.setVisibility(View.GONE);
                    txt_topbar.setText(R.string.reduction);
                }else{
                    //fTransaction.show(fg_reduction);
                    fTransaction.replace(R.id.content, fg_reduction);
                   // fTransaction.addToBackStack(null);
                    ajouter.setVisibility(View.GONE);
                    txt_topbar.setText(R.string.reduction);
                }
                break;
            case R.id.txt_setting:
                setSelected();
                txt_setting.setSelected(true);
                if(fg_setting == null){
                    fg_setting = new MyFragment("第三个Fragment");
                    fTransaction.add(R.id.content,fg_setting);
                    ajouter.setVisibility(View.GONE);
                    txt_topbar.setText(R.string.setting);
                }else{
                    fTransaction.replace(R.id.content, fg_setting);
                  //  fTransaction.addToBackStack(null);
                    ajouter.setVisibility(View.GONE);
                    txt_topbar.setText(R.string.setting);
                   // fTransaction.show(fg_setting);
                }
                break;

        }
        fTransaction.commit();
    }



}
