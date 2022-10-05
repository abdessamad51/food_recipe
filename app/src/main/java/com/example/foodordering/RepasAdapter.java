package com.example.foodordering;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class RepasAdapter extends ArrayAdapter {
    Context adapterContext;
    int adapterResource;
    ArrayList<Repas> adapterRepas;


    public RepasAdapter(@NonNull Context context, int resource, @NonNull   ArrayList<Repas> repasData) {
        super(context, resource, repasData);
        adapterContext= context;
        adapterResource=resource;
        adapterRepas = repasData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view ;
        LayoutInflater viewInflater = LayoutInflater.from(adapterContext);
        view = viewInflater.inflate(adapterResource,parent,false);
        TextView tv = (TextView) view.findViewById(R.id.nomRepas);
        ImageView iv  =(ImageView) view.findViewById(R.id.imageRepas);
        CardView cr = (CardView) view.findViewById(R.id.cardRepas);
        Repas re = adapterRepas.get(position);
//

        tv.setText(re.nomRepas);
        iv.setImageResource(adapterContext.getResources().getIdentifier(re.imgRepas,"drawable",adapterContext.getPackageName()));
        cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(adapterContext,DetailsRepasActivity.class);
                it.putExtra("nomRepas",re.nomRepas);
                 it.putExtra("ingredientsRepas",re.ingredientsRepas);
                it.putExtra("methodeRepas",re.methodeRepas);
                it.putExtra("imgRepas",re.imgRepas);
                adapterContext.startActivity(it);

            }
        });
        return  view;

    }
}
