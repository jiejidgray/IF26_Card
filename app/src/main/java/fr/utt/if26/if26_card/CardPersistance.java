package fr.utt.if26.if26_card;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;

public class CardPersistance extends SQLiteOpenHelper implements PersistanceInterface{

    public static final String DATABASE_NAME = "cards.db";
    public static final int DATABASE_VERSION = 1;
    private static final String Attribut_number = "CardNumber";
    private static final String TABLE_CARD = "card";
    private static final ByteArrayOutputStream photostream = new ByteArrayOutputStream();
    private static final Blob ATTRIBUT_CODEPHOTO = ;
    private static final Blob ATTRIBUT_TYPEPHOTO = ;
    private static final String ATTRIBUT_COMMENT = ;

//图片以Blob形式存入数据库，提取出来时转换成bitmap格式

    public void onCreate(SQLiteDatabase db) {

//顺序：卡号，条形码图，卡片种类图，注释
        final String table_card_create =
                "CREATE TABLE " + TABLE_CARD + "(" + ATTRIBUT_CARD + " TEXT primary key," + ATTRIBUT_CODEPHOTO + " BLOB, " + ATTRIBUT_TYPEPHOTO + " BLOB, " + ATTRIBUT_COMMENT + " TEXT" +
                        ")";
        db.execSQL(table_card_create);

    }

    public CardPersistance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    public initdata(){

    }

    public Card getCard(int position) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = select(TABLE_CARD);
        cursor.moveToPosition(position);
        db.query(TABLE_MODULES, new String[] { ATTRIBUT_SIGLE,
                        ATTRIBUT_CATEGORIE, ATTRIBUT_CREDIT,ATTRIBUT_RESULTAT }, ATTRIBUT_SIGLE + "=?",
                new String[] { String.valueOf(key) }, null, null, null, null);


    }

}
