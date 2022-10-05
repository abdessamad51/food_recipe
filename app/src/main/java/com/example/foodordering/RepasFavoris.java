package com.example.foodordering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class RepasFavoris extends AppCompatActivity {
    BD Repas = new BD(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas_favoris);
        ArrayList<Repas> repasList = Repas.listeRepasFavoris();
         RepasAdapter  ra = new RepasAdapter(RepasFavoris.this,R.layout.repas,repasList);
            ListView lR = (ListView) findViewById(R.id.RepasFavoris);
            lR.setAdapter(ra);
    }
}