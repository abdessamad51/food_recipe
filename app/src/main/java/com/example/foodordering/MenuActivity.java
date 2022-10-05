package com.example.foodordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity  {
    CardView cd;
    BD Repas = new BD(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (Repas.nombreRepas()==0) {
            String ingerdientsSalad = getResources().getString(R.string.ingredientsSalad) ;
            String preparationSalad = getResources().getString(R.string.preparationSalad) ;

            String ingerdientsCake = getResources().getString(R.string.ingredientsCake) ;
            String preparationCake = getResources().getString(R.string.preparationCake) ;

            String ingerdientsBurger = getResources().getString(R.string.ingredientsBurger) ;
            String preparationBurger = getResources().getString(R.string.preparationBurger) ;

            String ingerdientsPizza = getResources().getString(R.string.ingredientsPizza) ;
            String preparationPizza= getResources().getString(R.string.preparationPizza) ;

            String ingerdientsCookies = getResources().getString(R.string.ingredientsCookies) ;
            String preparationCookies= getResources().getString(R.string.preparationCookies) ;

            Repas.ajouteRepas("Salad","salad",ingerdientsSalad,preparationSalad);

            Repas.ajouteRepas("Cake","cake",ingerdientsCake,preparationCake);

            Repas.ajouteRepas("Burger","burger",ingerdientsBurger, preparationBurger );

            Repas.ajouteRepas("Pizza","pizza",ingerdientsPizza,preparationPizza);

            Repas.ajouteRepas("Cookies","cookies",ingerdientsCookies,preparationCookies);

            ArrayList<Repas> repasList = Repas.listeRepas();
            RepasAdapter  ra = new RepasAdapter(MenuActivity.this,R.layout.repas,repasList);
            ListView lR = (ListView) findViewById(R.id.ListRepas);
            lR.setAdapter(ra);

        }
        else {
            ArrayList<Repas> repasList = Repas.listeRepas();
            RepasAdapter  ra = new RepasAdapter(MenuActivity.this,R.layout.repas,repasList);
            ListView lR = (ListView) findViewById(R.id.ListRepas);
            lR.setAdapter(ra);
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.listefavroute) {
//            ArrayList<Repas> repasList = Repas.listeRepasFavoris();
//            RepasAdapter  ra = new RepasAdapter(MenuActivity.this,R.layout.repas,repasList);
//            ListView lR = (ListView) findViewById(R.id.ListRepas);
//            lR.setAdapter(ra);
            Intent it = new Intent(MenuActivity.this, RepasFavoris.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mon_menu,menu);
        MenuItem mi = menu.findItem(R.id.app_bar_search);
        SearchView sv = (SearchView) mi.getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Repas> repasList = Repas.recharcheRepas(s);
                RepasAdapter  ra = new RepasAdapter(MenuActivity.this,R.layout.repas,repasList);
                ListView lR = (ListView) findViewById(R.id.ListRepas);
                lR.setAdapter(ra);
                return false;
            }
        });
        return  true;
    }





}