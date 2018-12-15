package fr.utt.if26.if26_card;

import android.app.Person;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;

public class CardPersistance extends SQLiteOpenHelper implements PersistanceInterface {

    public static final String DATABASE_NAME = "cards.db";
    public static final int DATABASE_VERSION = 1;
    private static final String ATTRIBUT_NUMBER = "CardNumber";
    private static final String TABLE_CARD = "card";

    private static final String ATTRIBUT_CODEPHOTO = "codephoto";
    private static final String ATTRIBUT_TYPEPHOTO = "typephoto";
    private static final String ATTRIBUT_COMMENT = "comment";

//图片以Blob形式存入数据库，提取出来时转换成bitmap格式

    public void onCreate(SQLiteDatabase db) {

//顺序：卡号，条形码图，卡片种类图，注释
        final String table_card_create =
                "CREATE TABLE " + TABLE_CARD + "(" + ATTRIBUT_NUMBER + " TEXT primary key," + ATTRIBUT_CODEPHOTO + " BLOB, " + ATTRIBUT_TYPEPHOTO + " BLOB, " + ATTRIBUT_COMMENT + " TEXT" +
                        ")";
        db.execSQL(table_card_create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ContentValues c;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
        onCreate(db);

    }

    public CardPersistance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }


//取图
    //提取图片（提取出的格式为图片格式）
    public Bitmap getTypephotoBmap(String key) {
        SQLiteDatabase db = getReadableDatabase();
        String Query = " SELECT ATTRIBUT_TYPEPHOTO " +
                " FROM TABLE_CARD " + " WHERE ATTRIBUT_NUMBER = '" +  key +"'";
        Cursor cursor = db.rawQuery(Query,null);
        cursor.moveToFirst();
        byte[] resultatBlob = cursor.getBlob(1);
        Bitmap resultat = BitmapFactory.decodeByteArray(resultatBlob, 0, resultatBlob.length);
        return resultat;
                /*db.query(TABLE_CARD, new Blob(ATTRIBUT_TYPEPHOTO), ATTRIBUT_NUMBER + " =? ",
                new String[] { String.valueOf(key) }, null, null, null, null);*/
    }

//存图
    //将要存的图转换成二进制数组（即blob形式）
    public byte[] imgToArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

//将card数据插入数据库
    //sql指令不确定对不对。。。
    @Override
    public void addCard(Card card) {
        SQLiteDatabase db = getReadableDatabase();
        if (db.isOpen()) {
            db.execSQL("INSERT INTO " + TABLE_CARD + "(" + ATTRIBUT_NUMBER + "," + ATTRIBUT_CODEPHOTO + "," + ATTRIBUT_TYPEPHOTO + "," + ATTRIBUT_COMMENT + ") "
                    + "values (?, ?, ?, ?);",
                    new Object[]{card.getNumber(), imgToArray(card.getCodeImage()), imgToArray(card.getTypeImage()), card.getComment()});

        db.close();
        }

    }





    @Override
    public void initdata() {


    }

//还没做
    @Override
    public void delCard(Card c) {

    }

    @Override
    public void updateCard(Card c) {

    }

    //通过给定卡号来返回card
    public Card getCard(String key) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = " SELECT * " +
                " FROM " + TABLE_CARD + " WHERE "  + ATTRIBUT_NUMBER + " = '" +  key +"'";
        Cursor cursor = db.rawQuery(Query, null);

        String newnumber = cursor.getString(cursor.getColumnIndex(ATTRIBUT_NUMBER));
        Bitmap newcodephoto = BitmapFactory.decodeByteArray(cursor.getBlob(1),
                0, cursor.getBlob(1).length);
        Bitmap newtypephoto = BitmapFactory.decodeByteArray(cursor.getBlob(2),
                0, cursor.getBlob(2).length);
        String newcomment = cursor.getString(3);

        Card card = new Card(newnumber, newcodephoto, newtypephoto, newcomment);
        return card;


        /*db.query(TABLE_CARD, new String[] { ATTRIBUT_NUMBER,
                        ATTRIBUT_CODEPHOTO, ATTRIBUT_TYPEPHOTO,ATTRIBUT_COMMENT }, Attribut_NUMBER + "=?",
                new String[] { String.valueOf(key) }, null, null, null, null);
    */


    }

    @Override
    public int countCard() {
        return 0;
    }

    //提取所有的card
    @Override
    public ArrayList<Card> getallCard() {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = " SELECT * " +
                " FROM " + TABLE_CARD;
        Cursor cursor = db.rawQuery(Query, null);
        ArrayList<Card> listcard = new ArrayList<Card>();
        if (cursor.moveToFirst()) {
            do {
                Card card = new Card(cursor.getString(0),BitmapFactory.decodeByteArray(cursor.getBlob(1),
                        0, cursor.getBlob(1).length),
                        BitmapFactory.decodeByteArray(cursor.getBlob(2),
                                0, cursor.getBlob(2).length),cursor.getString(3));
                listcard.add(card);
            } while (cursor.moveToNext());

        }
        return listcard;
    }

}
