package com.example.foodordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText email,mdp;
    private Button cnx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText)findViewById(R.id.email);
        mdp = (EditText)findViewById(R.id.mdp);
        cnx = (Button) findViewById(R.id.connecte);
        BD utli = new BD (this);
        cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().length()!=0 && mdp.getText().toString().length()!=0) {


                    if (utli.verifierUtilisateur(email.getText().toString(), mdp.getText().toString())) {
                        Intent it = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(it);


                    } else {
                        Toast.makeText(LoginActivity.this, "Email ou mode pass incorrect ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "voulez vous remplir les champs ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}