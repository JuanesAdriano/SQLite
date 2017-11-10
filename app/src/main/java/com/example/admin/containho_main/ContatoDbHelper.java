package com.example.admin.containho_main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 09/11/2017.
 */

public class ContatoDbHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE = " CREATE TABLE IF NOT EXISTS" + ContatinhoContract.NOME_TABELA + "("+
            ContatinhoContract.COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ContatinhoContract.COLUNA_NOME + " TEXT,"+
            ContatinhoContract.COLUNA_TELEFONE + " TEXT,"+
            ContatinhoContract.COLUNA_INFO + " TEXT);";

    private static final String SQL_DROP = "DROP TABLE IF EXISTS" + ContatinhoContract.NOME_TABELA+";";
    private static final Integer DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "agenda.db";


    public ContatoDbHelper(Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntiga, int versionNova) {
        db.execSQL(SQL_DROP);
        onCreate(db);

    }
}
