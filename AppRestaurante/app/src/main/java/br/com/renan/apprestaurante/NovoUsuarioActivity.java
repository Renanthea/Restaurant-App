package br.com.renan.apprestaurante;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.renan.apprestaurante.database.UsuarioDao;
import br.com.renan.apprestaurante.model.Usuario;

public class NovoUsuarioActivity extends AppCompatActivity {

    private Context context;
    private EditText et_nome, et_senha;
    private Button btn_incluir;
    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_usuario);

        iniciarComponentes();
        executarAcao();
    }

    private void iniciarComponentes() {
        context = getBaseContext();
        et_nome = (EditText) findViewById(R.id.et_nome);
        et_senha = (EditText) findViewById(R.id.et_senha);
        btn_incluir = (Button) findViewById(R.id.btn_incluir);

        usuarioDao = new UsuarioDao(context);
    }

    private void executarAcao() {
        btn_incluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = et_nome.getText().toString();
                String senha = et_senha.getText().toString();
                String mensagem = "Usuário incluído com sucesso";
                try{
                    Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setSenha(senha);

                    usuarioDao.inserirUsuario(usuario);
                    Intent intent = getIntent();
                    boolean novoUsuario = intent.getBooleanExtra("novo", false);
                    if(novoUsuario){
                        usuarioDao.removerContato("admin");
                    }
                } catch (Exception e){
                    mensagem = "ERRO: " + e.getMessage();
                } finally {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(NovoUsuarioActivity.this)
                                    .setTitle("Erro")
                                    .setMessage(mensagem)
                                    .setPositiveButton("OK", null);
                    builder.create().show();
                    et_nome.setText("");
                    et_senha.setText("");
                }
            }
        });
    }
}
