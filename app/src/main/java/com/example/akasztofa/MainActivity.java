package com.example.akasztofa;

import static java.util.Arrays.*;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class MainActivity extends AppCompatActivity {
    private Button buttonMinus, buttonPlus, buttonTippel;
    private TextView textKarakterek, textSzoveg;
    private ImageView imageAkasztofa;
    private AlertDialog.Builder builderJatekVege;
    private int aktualisKarakter;
    private String kivalasztottSzo;
    private String tippeltSzoveg;
    private String vonalak;
    private ArrayList<Character> karakterek = new ArrayList<>();
    private ArrayList<Character> hasznaltKarakterek = new ArrayList<>();
    private int sikeresTippekSzama;
    private int sikertelenTippekSzama;
    private String hasznaltKatrakterekString;

    public void Init(){
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonTippel = findViewById(R.id.buttonTippel);
        textKarakterek = findViewById(R.id.textKarakterek);
        textSzoveg = findViewById(R.id.textSzoveg);
        imageAkasztofa = findViewById(R.id.imageAkasztofa);
        builderJatekVege = new AlertDialog.Builder(MainActivity.this);
        builderJatekVege.setCancelable(false)
                .setTitle("Nyertél! / Vesztettél!")
                .setMessage("Szeretne új játékot játszani?")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { Game(); }
                })
                .create();
        Collections.addAll(karakterek,'A', 'Á', 'B', 'C', 'D', 'E',
                'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L', 'M', 'N', 'O', 'Ó', 'Ö',
                'Ő', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'Ű', 'V', 'W', 'X', 'Y', 'Z');
        aktualisKarakter = 0;
        hasznaltKatrakterekString = "";
    }

    public void Lapozas(){


        buttonMinus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (aktualisKarakter == 0){
                    textKarakterek.setText(String.valueOf(karakterek.get(karakterek.size() - 1)));
                    aktualisKarakter = karakterek.size() - 1;
                } else {
                    textKarakterek.setText(String.valueOf(karakterek.get(aktualisKarakter - 1)));
                    aktualisKarakter -= 1;
                }
                if (hasznaltKarakterek.contains(karakterek.get(aktualisKarakter))){
                    textKarakterek.setTextColor(Color.parseColor("#FF000000"));
                } else {
                    textKarakterek.setTextColor(Color.parseColor("#FF0000"));
                }
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aktualisKarakter == karakterek.size() - 1){
                    textKarakterek.setText(String.valueOf(karakterek.get(0)));
                    aktualisKarakter = 0;
                } else {
                    textKarakterek.setText(String.valueOf(karakterek.get(aktualisKarakter + 1)));
                    aktualisKarakter += 1;
                }
                if (hasznaltKarakterek.contains(karakterek.get(aktualisKarakter))){
                    textKarakterek.setTextColor(Color.parseColor("#FF000000"));
                } else {
                    textKarakterek.setTextColor(Color.parseColor("#FF0000"));
                }
            }
        });
    }

    public void AkasztofaKep(){
        switch (sikertelenTippekSzama){
            case 1:
                imageAkasztofa.setImageResource(R.drawable.akasztofa01);
                break;
            case 2:
                imageAkasztofa.setImageResource(R.drawable.akasztofa02);
                break;
            case 3:
                imageAkasztofa.setImageResource(R.drawable.akasztofa03);
                break;
            case 4:
                imageAkasztofa.setImageResource(R.drawable.akasztofa04);
                break;
            case 5:
                imageAkasztofa.setImageResource(R.drawable.akasztofa05);
                break;
            case 6:
                imageAkasztofa.setImageResource(R.drawable.akasztofa06);
                break;
            case 7:
                imageAkasztofa.setImageResource(R.drawable.akasztofa07);
                break;
            case 8:
                imageAkasztofa.setImageResource(R.drawable.akasztofa08);
                break;
            case 9:
                imageAkasztofa.setImageResource(R.drawable.akasztofa09);
                break;
            case 10:
                imageAkasztofa.setImageResource(R.drawable.akasztofa10);
                break;
            case 11:
                imageAkasztofa.setImageResource(R.drawable.akasztofa11);
                break;
            case 12:
                imageAkasztofa.setImageResource(R.drawable.akasztofa12);
                break;
            case 13:
                imageAkasztofa.setImageResource(R.drawable.akasztofa13);
                break;
            default:
                imageAkasztofa.setImageResource(R.drawable.akasztofa00);
        }
    }

    public void TippEllenorzese(){
        hasznaltKarakterek.add(karakterek.get(aktualisKarakter));
        hasznaltKatrakterekString += karakterek.get(aktualisKarakter);
        textKarakterek.setTextColor(Color.parseColor("#FF000000"));
        boolean sikeres = false;
        String ujSzoveg = "";
        int talalatok = -1;
        for (int i = 0; i < kivalasztottSzo.length(); i++) {
            if (tippeltSzoveg.charAt(i) == '_'){
                if (karakterek.get(aktualisKarakter) == kivalasztottSzo.charAt(i)){
                    ujSzoveg += kivalasztottSzo.charAt(i);
                    sikeres = true;
                    talalatok++;
                } else {
                    ujSzoveg += "_";
                }
            } else {
                ujSzoveg += tippeltSzoveg.charAt(i);
            }
        }
        if (sikeres){
            sikeresTippekSzama++;
            sikeresTippekSzama += talalatok;
        } else {
            sikertelenTippekSzama++;
            AkasztofaKep();
        }
        tippeltSzoveg = ujSzoveg;
        vonalak = "";
        for (int i = 0; i < ujSzoveg.length(); i++) {
            vonalak += ujSzoveg.charAt(i) + " ";
        }
        textSzoveg.setText(vonalak);
        if (sikeresTippekSzama == kivalasztottSzo.length()){
            builderJatekVege.setTitle("Nyertél!").create().show();
        }
        if (sikertelenTippekSzama == 13){
            builderJatekVege.setTitle("Vesztettél!").create().show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("aktualisKarakter", aktualisKarakter);
        outState.putString("vonalak", vonalak);
        outState.putString("hasznaltKarakterekString", hasznaltKatrakterekString);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        aktualisKarakter = savedInstanceState.getInt("aktualisKarakter");
        vonalak = savedInstanceState.getString("vonalak");
        hasznaltKatrakterekString = savedInstanceState.getString("hasznaltKarakterekString");
        for (char x: hasznaltKatrakterekString.toCharArray()) {
            hasznaltKarakterek.add(x);
        }
    }

    public void Game(){
        ArrayList<String> szavakLista = new ArrayList<String>(asList("acélos", "bestia", "cafka",
                "csermely", "felleg", "ízes", "jégvirág", "katlan", "lokni", "spulni"));
        Random rnd = new Random();
        hasznaltKarakterek.clear();
        sikeresTippekSzama = 0;
        sikertelenTippekSzama = 0;
        imageAkasztofa.setImageResource(R.drawable.akasztofa00);
        kivalasztottSzo = szavakLista.get((rnd.nextInt(szavakLista.size()) + 1) - 1).toUpperCase(Locale.ROOT);
        tippeltSzoveg = "";
        vonalak = "";
        for (int i = 0; i < kivalasztottSzo.length(); i++) {
            vonalak += "_ ";
            tippeltSzoveg += "_";
        }
        textSzoveg.setText(vonalak);
        //buttonTippel.setText(kivalasztottSzo);
        buttonTippel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasznaltKarakterek.contains(karakterek.get(aktualisKarakter))){
                    Toast.makeText(getApplication().getApplicationContext(),"Ez a karaktert már használta!", Toast.LENGTH_SHORT).show();
                } else {
                    TippEllenorzese();
                }
            }
        });




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        Lapozas();
        Game();
    }
}