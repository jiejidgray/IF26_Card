package fr.utt.if26.if26_card;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;

public class Card {
    //顺序：卡号，条形码图，卡片种类图，注释
    private String Number;
    private Bitmap Codephoto;
    private Bitmap Typephoto;
    private String Comment;
    private String TypeName;


    public Card(String Number, Bitmap Codephoto, Bitmap Typephoto, String Comment, String TypenName) {
        this.Number = Number;
        this.Codephoto = Codephoto;
        this.Typephoto = Typephoto;
        this.Comment = Comment;
        this.TypeName = TypenName;


    }

    public Bitmap getTypeImage() {
        return this.Typephoto;
    }

    public Bitmap getCodeImage() {
        return this.Codephoto;
    }

    public String getNumber () {
        return this.Number;
    }

    public String getComment () {
        return this.Comment;
    }

    public void setTypephoto(Bitmap typephoto) {
        Typephoto = typephoto;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public void setCodephoto(Bitmap codephoto) {
        Codephoto = codephoto;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

   /* public Bitmap readImage(){
        Bitmap bitmap= BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/sephora.png"));

        return bitmap;
    }*/
}
