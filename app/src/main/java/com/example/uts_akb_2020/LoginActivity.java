package com.example.uts_akb_2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editusername);
        password = (EditText) findViewById(R.id.editpassword);
        btnlogin = (Button) findViewById(R.id.login);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = username.getText().toString();
                String pass = password.getText().toString();
                if (nama.isEmpty() || pass.isEmpty()){
                    showErrorLogin();
                } else {
                    if (nama.equals("admin") && pass.equals("admin")){
                        successLogin();
                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                    } else {
                        errorLogin();
                    }
                }
            }
        });
    }

    public void showErrorLogin(){
        Toast.makeText(this, "Error Username dan Password tidak Valid !",
                Toast.LENGTH_SHORT).show();
    }
    public void successLogin(){
        Toast.makeText(this, "Anda Berhasil Login !", Toast.LENGTH_SHORT).show();
    }
    public void errorLogin(){
        Toast.makeText(this, "Username atau Password Salah !",
                Toast.LENGTH_SHORT).show();
    }




}