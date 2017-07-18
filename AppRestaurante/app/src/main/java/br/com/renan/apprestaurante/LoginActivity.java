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

public class LoginActivity extends AppCompatActivity {

    private Context context;
    private EditText et_nome, et_senha;
    private Button btn_login;
    private UsuarioDao usuarioDao;
    private boolean novoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        iniciarComponentes();
        executarAcao();
    }

    private void iniciarComponentes() {
        context = getBaseContext();
        et_nome = (EditText) findViewById(R.id.et_nome);
        et_senha = (EditText) findViewById(R.id.et_senha);
        btn_login = (Button) findViewById(R.id.btn_login);

        usuarioDao = new UsuarioDao(context);
        //usuarioDao.removerTudo();
        int numContatos = usuarioDao.contarContatos();

        if(numContatos == 0){
            Usuario usuario = new Usuario();
            usuario.setNome("admin");
            usuario.setSenha("admin");
            usuarioDao.inserirUsuario(usuario);
            novoUsuario = true;
        } else {
            novoUsuario = false;
        }
    }

    private void executarAcao() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = et_nome.getText().toString();
                String senha = et_senha.getText().toString();

                if(usuarioDao.validarUsuario(nome, senha) == null){
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("Erro")
                                    .setMessage("Usuário ou senha inválidos")
                                    .setPositiveButton("OK", null);
                    builder.create().show();
                    et_nome.setText("");
                    et_senha.setText("");

                } else {
                    if(novoUsuario){
                        Intent intent = new Intent(context, NovoUsuarioActivity.class);
                        intent.putExtra("novo", novoUsuario);
                        startActivity(intent);
                        finish();
                    } else {
                        //usuarioDao.removerContato("admin");
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}
