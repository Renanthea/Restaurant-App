package br.com.renan.apprestaurante.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class Dao {

    private Context context;

    protected SQLiteDatabase db;

    public Dao(Context context) {
        this.context = context;
    }

    //metodo para abrir a conexao com o banco de dados
    public void abrirConexao(){
        SQLiteHelper helper = new SQLiteHelper(
                context,
                Constantes.DATABASE,
                null,
                Constantes.VERSAO);
        db = helper.getWritableDatabase(); //realiza a criacao do banco
    }

    public void fecharConexao(){
        if(db != null){
            db.close();
        }
    }
}
