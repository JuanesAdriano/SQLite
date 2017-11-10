package com.example.admin.containho_main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Admin on 09/11/2017.
 */

public class ContatinhoDAO {
    private ContatoDbHelper contatinhoDBHelper;

    public ContatinhoDAO(Context context){
        this.contatinhoDBHelper = new ContatoDbHelper(context);
    }
    public boolean inserirContatinho(Contatinho crush){
        SQLiteDatabase db = this.contatinhoDBHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContatinhoContract.COLUNA_NOME, crush.getNome());
        valores.put(ContatinhoContract.COLUNA_TELEFONE, crush.getTelefone());
        valores.put(ContatinhoContract.COLUNA_INFO, crush.getInfos());
        long linhas =  db.insert(ContatinhoContract.NOME_TABELA, null, valores);
        return linhas == -1 ? false : true;

    }
    public boolean deleteContainho(Integer id){
        SQLiteDatabase db = this.contatinhoDBHelper.getWritableDatabase();

        String condicao = ContatinhoContract.COLUNA_ID+" = ?";
        String[] argumentos ={id.toString()};


        long linhas = db.delete(ContatinhoContract.NOME_TABELA, condicao, argumentos);
        return linhas > 0;
    }
    public boolean editarContatinho(Contatinho crush){
        SQLiteDatabase db = this.contatinhoDBHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContatinhoContract.COLUNA_NOME, crush.getNome());
        valores.put(ContatinhoContract.COLUNA_TELEFONE, crush.getTelefone());
        valores.put(ContatinhoContract.COLUNA_INFO, crush.getInfos());

        String condicao = ContatinhoContract.COLUNA_ID+" = ?";
        String[] argumentos ={crush.getId().toString()};

        long linhas = db.update(ContatinhoContract.NOME_TABELA, valores, condicao, argumentos);
        return linhas != 1;
    }

    public ArrayList<Contatinho> bucarTodosContatinhs(){
        SQLiteDatabase db = this.contatinhoDBHelper.getReadableDatabase();
        String[] colunas = {ContatinhoContract.COLUNA_ID, ContatinhoContract.COLUNA_NOME, ContatinhoContract.COLUNA_INFO, ContatinhoContract.COLUNA_TELEFONE};

        Cursor cursor = db.query(ContatinhoContract.NOME_TABELA, colunas, null, null, null, null, ContatinhoContract.COLUNA_NOME+" ASC");
        ArrayList<Contatinho> crushs = new ArrayList<Contatinho>();
        while(cursor.moveToNext()){
            Contatinho contato = new Contatinho();
            contato.setId(cursor.getInt(cursor.getColumnIndex(ContatinhoContract.COLUNA_ID)));
            contato.setNome(cursor.getString(cursor.getColumnIndex(ContatinhoContract.COLUNA_NOME)));
            contato.setTelefone(cursor.getString(cursor.getColumnIndex(ContatinhoContract.COLUNA_TELEFONE)));
            contato.setInfos(cursor.getString(cursor.getColumnIndex(ContatinhoContract.COLUNA_INFO)));
            crushs.add(contato);
        }
        cursor.close();
        return crushs;
    }
}
