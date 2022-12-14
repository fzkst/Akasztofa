package com.example.akasztofa;

import static java.util.Arrays.*;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
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
    private Button buttonMinus, buttonPlus, buttonTippel, buttonUjSzo;
    private TextView textKarakterek, textSzoveg;
    private EditText editTextUjSzo;
    private ImageView imageAkasztofa;
    private AlertDialog.Builder builderJatekVege;
    private int aktualisKarakter;
    private String kivalasztottSzo;
    private String tippeltSzoveg;
    private String vonalak;
    private ArrayList<Character> karakterek = new ArrayList<>();
    private ArrayList<Character> hasznaltKarakterek = new ArrayList<>();
    private ArrayList<String> mentettSzavak = new ArrayList<>();
    private int sikeresTippekSzama;
    private int sikertelenTippekSzama;
    private String hasznaltKatrakterekString;

    public void Init(){
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonTippel = findViewById(R.id.buttonTippel);
        buttonUjSzo = findViewById(R.id.buttonUjSzo);
        textKarakterek = findViewById(R.id.textKarakterek);
        textSzoveg = findViewById(R.id.textSzoveg);
        imageAkasztofa = findViewById(R.id.imageAkasztofa);
        editTextUjSzo = findViewById(R.id.editTextujSzo);
        builderJatekVege = new AlertDialog.Builder(MainActivity.this);
        builderJatekVege.setCancelable(false)
                .setTitle("Nyert??l! / Vesztett??l!")
                .setMessage("Szeretn??l ??j j??t??kot j??tszani?")
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
        Collections.addAll(karakterek,'A', '??', 'B', 'C', 'D', 'E',
                '??', 'F', 'G', 'H', 'I', '??', 'J', 'K', 'L', 'M', 'N', 'O', '??', '??',
                '??', 'P', 'Q', 'R', 'S', 'T', 'U', '??', '??', '??', 'V', 'W', 'X', 'Y', 'Z');
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
            builderJatekVege.setTitle("Nyert??l!").create().show();
        }
        if (sikertelenTippekSzama == 13){
            builderJatekVege.setTitle("Vesztett??l!\nA megfejt??s: " + kivalasztottSzo).create().show();
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
        ArrayList<String> szavakLista = new ArrayList<String>(asList("ac??los", "bestia", "cafka",
                "csermely", "felleg", "??zes", "j??gvir??g", "katlan", "lokni", "spulni"));
        mentettSzavak = new ArrayList<>(asList("ac??los", "bestia", "cafka",
                "csermely", "felleg", "??zes", "j??gvir??g", "katlan", "lokni", "spulni"));
        Random rnd = new Random();
        hasznaltKarakterek.clear();
        sikeresTippekSzama = 0;
        sikertelenTippekSzama = 0;
        textKarakterek.setTextColor(Color.parseColor("#FF0000"));
        imageAkasztofa.setImageResource(R.drawable.akasztofa00);
        kivalasztottSzo = szavakLista.get((rnd.nextInt(szavakLista.size()) + 1) - 1).toUpperCase(Locale.ROOT);
        tippeltSzoveg = "";
        vonalak = "";
        for (int i = 0; i < kivalasztottSzo.length(); i++) {
            vonalak += "_ ";
            tippeltSzoveg += "_";
        }

        textSzoveg.setText(vonalak);
        buttonTippel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasznaltKarakterek.contains(karakterek.get(aktualisKarakter))){
                    Toast.makeText(getApplication().getApplicationContext(),"Ez a karaktert m??r haszn??lta!", Toast.LENGTH_SHORT).show();
                } else {
                    TippEllenorzese();
                }
            }
        });

        buttonUjSzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(editTextUjSzo) != ""){
                    editTextUjSzo.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    szavakLista.add(String.valueOf(editTextUjSzo));
                    mentettSzavak.add(String.valueOf(editTextUjSzo));
                    editTextUjSzo.setText(String.valueOf(""));
                    SaveData();
                }
            }
        });
    }

    private void SaveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mentettSzavak);
        editor.putString("task list", json);
        editor.apply();
    }

    private void LoadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        mentettSzavak = gson.fromJson(json, type);

        if (mentettSzavak == null) {
            mentettSzavak = new ArrayList<>();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        Lapozas();
        LoadData();
        Game();
    }
}