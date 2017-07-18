package br.com.renan.apprestaurante.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by emilio on 01/07/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //criamos o banco de dados com base em uma instrucao
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE if not exists [" + Constantes.TABELA +
                    "] ([" + Constantes.NOME + "] VARCHAR(20) NOT NULL, [" +
                    Constantes.SENHA + "] VARCHAR(32) NOT NULL" +
                    ", CONSTRAINT [] PRIMARY KEY ([" +
                    Constantes.NOME + "]))");

            db.execSQL(sb.toString());

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            StringBuilder sb = new StringBuilder();
            sb.append("DROP Table if exists [" + Constantes.TABELA + "]");
            db.execSQL(sb.toString());
            onCreate(db);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
