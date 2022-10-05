package com.example.foodordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsRepasActivity extends AppCompatActivity {
      TextView nomRepas;
     TextView ingredientsRepas;
      TextView  methodeRepas;
      ImageView imgRepas;
    Intent it;
    BD bd = new BD(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_repas);
        nomRepas = findViewById(R.id.repasNom);
        ingredientsRepas = findViewById(R.id.repasIngredients);
        methodeRepas = findViewById(R.id.repasMethode);
        imgRepas = (ImageView) findViewById(R.id.imgRepas);

        it = getIntent();
        String Nom = it.getExtras().getString("nomRepas");
        String content = it.getExtras().getString("ingredientsRepas");
        String methode = it.getExtras().getString("methodeRepas");
        String nomImage = it.getExtras().getString("imgRepas");

        nomRepas.setText(Nom);
        ingredientsRepas.setText(content);
        methodeRepas.setText(methode);
        imgRepas.setImageResource(DetailsRepasActivity.this.getResources().getIdentifier(nomImage,"drawable",DetailsRepasActivity.this.getPackageName()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_repas,menu);
        MenuItem mi = menu.findItem(R.id.AjouteRepasFavori);
//        BD bd = new BD(this);
//        SearchView sv = (SearchView) mi.getActionView();
//        sv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               if (bd.RepasFavorite(it.getExtras().getString("nomRepas"))) {
//                   Toast.makeText(DetailsRepasActivity.this, "le repas ajoute dans liste favori", Toast.LENGTH_SHORT).show();
//               }
//            }
//        });

        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        if(i==R.id.AjouteRepasFavori)
        {
            bd.RepasFavorite(it.getExtras().getString("nomRepas"));
            Toast.makeText(DetailsRepasActivity.this, "le repas ajoute dans liste favori", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}