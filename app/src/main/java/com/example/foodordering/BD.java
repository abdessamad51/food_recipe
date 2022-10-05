package com.example.foodordering;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BD extends SQLiteOpenHelper {
    public BD(@Nullable Context context) {
        super(context,"RecetteRepas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbs) {
        dbs.execSQL("create table utilisateur(id INTEGER PRIMARY KEY  AUTOINCREMENT,nom TEXT ,email TEXT ,mdp TEXT )");
        dbs.execSQL("create table Repas(id INTEGER PRIMARY KEY AUTOINCREMENT,nom TEXT,nomimage TEXT,ingredients TEXT,preparation TEXT,favori TEXT DEFAULT 'false')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase dbs, int i, int i1) {
        dbs.execSQL("drop Table if exists utilisateur");
        dbs.execSQL("drop Table if exists Repas");
    }
    public Boolean ajouteUtilisateur(String nom,String email,String mdp) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put("nom",nom);
        con.put("email",email);
        con.put("mdp",mdp);
        long result = myDB.insert("utilisateur",null,con);
        if(result==-1) {
            return  false;
        }
        else {
            return true;
        }
    }
    public Boolean ajouteRepas(String nom,String nomImage,String ingredients, String preparation) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put("nom",nom);
        con.put("nomimage",nomImage);
        con.put("ingredients",ingredients);
        con.put(" preparation", preparation);

        long result = myDB.insert("Repas",null,con);

        if(result==-1) {
            return  false;
        }
        else {
            return true;
        }
    }
    public Boolean RepasFavorite(String nom) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put("favori","true");

        myDB.update("Repas",con,"nom=?",new String[]{nom});
        myDB.close();
        return  true;
    }



    public ArrayList<Repas> listeRepas() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM Repas", null);
        ArrayList<Repas> al = new ArrayList<Repas>();
        cur.moveToFirst();
        while (cur.isAfterLast() == false) {
            int id;
            String nom, nomimage, ingredients, preparation;
            id = cur.getInt(0);
            nom = cur.getString(1);
            nomimage = cur.getString(2);
            ingredients = cur.getString(3);
            preparation = cur.getString(4);
            al.add(new Repas(nom, nomimage, ingredients, preparation));
            cur.moveToNext();

        }
        db.close();
        return  al;
    }
    public ArrayList<Repas> recharcheRepas(String var) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM Repas where nom like ? ",new String[]{"%" +var +"%"});
        ArrayList<Repas> al = new ArrayList<Repas>();
        cur.moveToFirst();
        while (cur.isAfterLast() == false) {
            int id;
            String nom, nomimage, ingredients, preparation;
            //id = cur.getInt(0);
            nom = cur.getString(1);
            nomimage = cur.getString(2);
            ingredients = cur.getString(3);
            preparation = cur.getString(4);
            al.add(new Repas(nom, nomimage, ingredients, preparation));
            cur.moveToNext();

        }
        db.close();
        return  al;
    }
    public Integer nombreRepas() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM Repas", null);
        return cur.getCount();
    }



    public Boolean verifierUtilisateur(String email,String mdp) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from utilisateur where email = ? and mdp = ? ",new String[] {email,mdp}  );

        if(cursor.getCount()>0) {
            return  true;
        } else {
            return false;
        }
    }
    public ArrayList<Repas>  listeRepasFavoris() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM Repas where favori = 'true'",null);
        ArrayList<Repas> al = new ArrayList<Repas>();
        cur.moveToFirst();
        while (cur.isAfterLast() == false) {
            int id;
            String nom, nomimage, ingredients, preparation;
            //id = cur.getInt(0);
            nom = cur.getString(1);
            nomimage = cur.getString(2);
            ingredients = cur.getString(3);
            preparation = cur.getString(4);
            al.add(new Repas(nom, nomimage, ingredients, preparation));
            cur.moveToNext();

        }
        db.close();
        return  al;
    }



}
