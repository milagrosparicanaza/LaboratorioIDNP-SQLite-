package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener{
EditText us, pas, nom, ap;
Button reg, can;
daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        us = (EditText) findViewById(R.id.TextV_RegUsers);
        pas = (EditText) findViewById(R.id.TextV_RegPass);
        nom = (EditText) findViewById(R.id.TextV_RegNom);
        ap = (EditText) findViewById(R.id.TextV_RegApe);
        reg = (Button) findViewById(R.id.Btn_RegRegistrar);
        can = (Button) findViewById(R.id.Btn_RegCancel);

        reg.setOnClickListener(this);
        can.setOnClickListener(this);
        dao = new daoUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Btn_RegRegistrar:
                Usuario u = new Usuario();
                u.setUsuario(us.getText().toString());
                u.setPassword(pas.getText().toString());
                u.setNombre(nom.getText().toString());
                u.setApellidos(ap.getText().toString());
                if(!u.isNull()){
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(dao.insertUsuario(u)){
                    Toast.makeText(this, "Registro Exitoso!!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Register.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(this, "Usuario ya Registrado!!!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Btn_RegCancel:
                Intent i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}