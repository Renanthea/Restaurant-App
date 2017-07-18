package br.com.renan.apprestaurante.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import br.com.renan.apprestaurante.model.Usuario;


/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class UsuarioDao extends Dao {

    public UsuarioDao(Context context) {
        super(context);
    }

    //metodo para inserir um novo usuario
    public void inserirUsuario(Usuario usuario){
        try {
            abrirConexao();
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NOME, usuario.getNome());
            cv.put(Constantes.SENHA, usuario.getSenha());

            db.insert(Constantes.TABELA, null, cv);

        } catch (Exception e){
            throw e;
        } finally {
            fecharConexao();
        }
    }

    //metodo para remover um usuario pelo nome
    public void removerContato(String nome){
        try {
            abrirConexao();
            String filtro = " nome = ? ";
            String[] argumentos = { nome };
            db.delete(Constantes.TABELA, filtro, argumentos);
        } catch (Exception e){
            throw e;
        } finally {
            fecharConexao();
        }
    }

    //metodo para remover um usuario pelo nome
    public void removerTudo(){
        try {
            abrirConexao();
            db.delete(Constantes.TABELA, null, null);
        } catch (Exception e){
            throw e;
        } finally {
            fecharConexao();
        }
    }

    //metodo para obter um usuario pelo nome
    public Usuario validarUsuario(String nome, String senha){
        Usuario usuario = null;
        Cursor cursor = null;
        try {
            abrirConexao();
            String[] argumentos = { nome, senha };
            String sql = " select * from " + Constantes.TABELA + " where nome = ? and senha = ? ";

            cursor = db.rawQuery(sql, argumentos);
            if(cursor.moveToNext()){
                usuario = new Usuario();

                usuario.setNome(nome);
                usuario.setSenha(senha);
            }
        } catch (Exception e){
            throw e;
        } finally {
            fecharConexao();
        }
            return usuario;
    }

    public int contarContatos(){
        Cursor cursor = null;
        int count = 0;
        try {
            abrirConexao();
            String sql = " select * from " + Constantes.TABELA;
            cursor = db.rawQuery(sql, null);

            while (cursor.moveToNext()){
                count++;
            }
        } catch (Exception e){
            throw e;
        } finally {
            fecharConexao();
        }
        return count;
    }
}
