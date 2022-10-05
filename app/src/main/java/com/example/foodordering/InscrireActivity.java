package com.example.foodordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InscrireActivity extends AppCompatActivity {
    private Button BtnInscrire;
    private EditText email,mdp,nom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire);
        nom = (EditText)findViewById(R.id.Nom);
        email = (EditText)findViewById(R.id.email);
        mdp = (EditText)findViewById(R.id.mdp);
        BtnInscrire = findViewById(R.id.inscrire);
        BD utli = new BD(this);
        BtnInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nom.getText().toString().length() !=0 && email.getText().toString().length()!=0 && mdp.getText().toString().length()!=0) {
                    if(utli.ajouteUtilisateur(nom.getText().toString(),email.getText().toString(),mdp.getText().toString())) {
                        Intent it = new Intent(InscrireActivity.this,MenuActivity.class);
                        startActivity(it);

                    }
                    else {
                        Toast.makeText(InscrireActivity.this, "Le compte il existe ", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(InscrireActivity.this, "voulez vous remplir les champs ", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

}