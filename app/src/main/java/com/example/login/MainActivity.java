package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText user, pass;
    Button btnEntrar, btnRegistrar;
    daoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.TextV_usu);
        pass = (EditText) findViewById(R.id.TextV_pass);
        btnEntrar = (Button) findViewById(R.id.Btn_iniciar);
        btnRegistrar = (Button) findViewById(R.id.Btn_registar);

        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        dao = new daoUsuario(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Btn_iniciar:
                String u=user.getText().toString();
                String p = pass.getText().toString();
                if(u.equals("") && p.equals("")){
                    Toast.makeText(this,"ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(dao.login(u,p)==1){
                    Usuario ux= dao.getUsuario(u,p);
                    Toast.makeText(this,"Datos correctos", Toast.LENGTH_LONG).show();
                    Intent i2= new Intent(MainActivity.this, Usuario.class);
                    i2.putExtra("id", ux.getId());
                    startActivity(i2);
                }
                break;
            case R.id.Btn_registar:
                Intent i= new Intent(MainActivity.this, Register.class);
                startActivity(i);
                break;
        }
    }
}